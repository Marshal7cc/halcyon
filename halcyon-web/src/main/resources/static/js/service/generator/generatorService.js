/**
 * author:Marshal
 * description:service层
 */
app.service("generatorService", function ($http) {
    this.generate = function (generatorInfo) {
        return $http.post(baseContextPath + "/generator/create", generatorInfo);
    };

    this.allTables = function () {
        return $http.get(baseContextPath + "/generator/alltables");
    }
});