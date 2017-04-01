app.factory('songService', ['$http', function ($http) {
    var obj = {};
    obj.getSongs = function () {
        return $http.get('songs');
    }

    return obj;
}]);