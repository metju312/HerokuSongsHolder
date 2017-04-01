var app = angular
        .module("myModule",["ui.router"])
        .filter('trustAsResourceUrl', ['$sce', function($sce) {
            return function(val) {
                return $sce.trustAsResourceUrl(val);
            };
        }])
        .config(['$locationProvider', function($locationProvider) {
            $locationProvider.hashPrefix('');
        }])
        .config(["$stateProvider", function($stateProvider){
            $stateProvider
                .state("home", {
                    url: "/",
                    templateUrl: "partial/home.html",
                    controller: "songsController"
                })
                .state("songs", {
                    url: "/songs",
                    templateUrl: "partial/songs.html",
                    controller: "songsController",
                    resolve:  {
                        songsList: ['$http', function ($http) {
                            return $http.get('songs')
                                .then(function (response) {
                                    return response.data;
                                })
                        }]
                    }
                })
                .state("songByTitle", {
                    url: "/songs/:title",
                    templateUrl: "partial/song.html",
                    controller: "songController"
                })
                .state("addSong", {
                    url: "/addSong",
                    templateUrl: "partial/addSong.html",
                    controller: "songsController"
                })
        }])
        .controller("homeController", function ($scope, $http, $log, $location, $anchorScroll){

        })
        .controller("songsController", ['$scope','$http','$log','$state','$location','$anchorScroll', 'songs','songsList', function ($scope, $http, $log, $state, $location, $anchorScroll, songs, songsList){
            // var vm = this;
            // vm.reloadData = function () {
            //     $state.reload();
            // };
            $scope.songs = songsList;

            $scope.go = function ( path ) {
                $location.path( path );
            };



            $scope.newYtDatas = [];

            $scope.addNewYtData = function () {
                $scope.newYtDatas.push({
                    iframe: ""
                });
            };

            $scope.newLinks = [];

            $scope.addNewLink = function () {
                $scope.newLinks.push({
                    title: "",
                    url: ""
                });
            };

            $scope.addNewSong = function (song) {
                song.links = $scope.newLinks;
                song.yTDatas = $scope.newYtDatas;
                $http.post('songs', song).then(function(response) {
                    console.log("Dodało");
                    $scope.go('/');
                    $scope.reloadRoute();
                })
            };

            $scope.reloadRoute = function() {
                $route.reload();
            }

            $scope.newSong = {};
        }])
        .controller("songController", function ($scope, $http, $log, $location, $anchorScroll, $routeParams){
            $http({
                method:'GET',
                url:"/songs/search/findByTitle?title=" + $routeParams.title})
                    .then(function (response) {
                        $scope.song = response.data._embedded.songs[0];
                        $http({
                            method:'GET',
                            url:$scope.song._links.ytDatas.href})
                            .then(function (response) {
                                $scope.yTDatas = response.data._embedded.yTDatas;
                            }, function (reason) {
                                $scope.error = reason;
                            });
                        $http({
                            method:'GET',
                            url:"/links/songid/" + $scope.id})
                            .then(function (response) {
                                $scope.links = response.data;
                            }, function (reason) {
                                $scope.error = reason;
                            });
                    }, function (reason) {
                        $scope.error = reason;
                    });
        })
        .controller("addSongController", function ($scope, $http, $log, $location, $anchorScroll, $routeParams){

        });

$(document).ready(function () {
    $('[data-toggle="offcanvas"]').click(function () {
        $('#side-menu').toggleClass('hidden-xs')
    })
});

$(function() {
    $("#link-list li").click(function () {
        if ( $("#link-list li").hasClass("active") ) {
            $("#link-list li").removeClass("active");
        }
        $(this).addClass("active");
    });
});

