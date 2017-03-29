var app = angular
        .module("myModule",["ngRoute"])
        .filter('trustAsResourceUrl', ['$sce', function($sce) {
            return function(val) {
                return $sce.trustAsResourceUrl(val);
            };
        }])
        .config(['$locationProvider', function($locationProvider) {
            $locationProvider.hashPrefix('');
        }])
        .config(["$routeProvider", function($routeProvider){
            $routeProvider
                .when("/", {
                    templateUrl: "partial/home.html",
                    controller: "songsController"
                })
                .when("/songs", {
                    templateUrl: "partial/songs.html",
                    controller: "songsController"
                })
                .when("/songs/:title", {
                    templateUrl: "partial/song.html",
                    controller: "songController"
                })
                .when("/addSong", {
                    templateUrl: "partial/addSong.html",
                    controller: "addSongController"
                })
        }])
        .controller("homeController", function ($scope, $http, $log, $location, $anchorScroll){

        })
        .controller("songsController", function ($scope, $http, $log, $location, $anchorScroll){

            $http({
                method:'GET',
                url:"/songs"})
                    .then(function (response) {
                        $scope.songs = response.data;
                    }, function (reason) {
                        $scope.error = reason;
                    });

            $scope.go = function ( path ) {
                $location.path( path );
            };
        })
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
                            url:$scope.song._links.links.href})
                            .then(function (response) {
                                $scope.links = response.data._embedded.links;
                            }, function (reason) {
                                $scope.error = reason;
                            });
                    }, function (reason) {
                        $scope.error = reason;
                    });
        })
        .controller("addSongController", function ($scope, $http, $log, $location, $anchorScroll, $routeParams){
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

            $scope.submitNewSong = function () {
                //post ytdata and links here
            };
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

