/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function RemoteResourceOrdenServicio($http, $q, baseUrl) {
    
    this.get = function (idorden) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/getProductoVenta/' + idorden
        }).success(function (data, status, headers, config) {
            defered.resolve(data);
        }).error(function (data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });

        return promise;

    };

    this.list = function () {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/ListaOrdenServicios'
        }).success(function (data, status, headers, config) {
            defered.resolve(data);
        }).error(function (data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });


        return promise;
    };

    this.insert = function (orden) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'POST',
            url: baseUrl + '/api/insertProductoVenta',
            data: orden
        }).success(function (data, status, headers, config) {
            defered.resolve(data);
        }).error(function (data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });

        return promise;
    };

    this.update = function (idorden, orden) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'PUT',
            url: baseUrl + '/api/updateProducto/' + idorden,
            data: orden
        }).success(function (data, status, headers, config) {
            defered.resolve(data);
        }).error(function (data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });

        return promise;
    };

    this.delete = function (idorden) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'DELETE',
            url: baseUrl + '/api/deleteProducto/' + idorden
        }).success(function (data, status, headers, config) {
            defered.resolve(data);
        }).error(function (data, status, headers, config) {
            if (status === 400) {
                defered.reject(data);
            } else {
                throw new Error("Fallo obtener los datos:" + status + "\n" + data);
            }
        });

        return promise;
    };

}

