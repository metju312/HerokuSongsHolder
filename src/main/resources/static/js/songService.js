app.factory('songService', ['$http', function ($http) {
    var obj = {};
    obj.getSongs = function () {
        return $http.get('songs');
    };
    obj.getSongByTitle = function (title) {
        return $http.get("/songs/search/findByTitle?title=" + title);
    };
    obj.getLinks = function (song) {
        return $http.get(song._links.links.href);
    };
    obj.getYtDatas = function (song) {
        return $http.get(song._links.ytDatas.href);
    };

    return obj;
}]);


// $http({
//     method:'GET',
//     url:"/songs/search/findByTitle?title=" + $routeParams.title})
//     .then(function (response) {
//         $scope.song = response.data._embedded.songs[0];
//         $http({
//             method:'GET',
//             url:$scope.song._links.ytDatas.href})
//             .then(function (response) {
//                 $scope.yTDatas = response.data._embedded.yTDatas;
//             }, function (reason) {
//                 $scope.error = reason;
//             });
//         $http({
//             method:'GET',
//             url:"/links/songid/" + $scope.id})
//             .then(function (response) {
//                 $scope.links = response.data;
//             }, function (reason) {
//                 $scope.error = reason;
//             });
//     }, function (reason) {
//         $scope.error = reason;
//     });