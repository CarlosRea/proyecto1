/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function RemoteResourceVenta($http, $q, baseUrl) {
    
    this.get = function (idcompra) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/getProductoVenta/' + idcompra
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
            url: baseUrl + '/api/ListaVentas'
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

    this.insert = function (venta) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'POST',
            url: baseUrl + '/api/insertProductoVenta',
            data: venta
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

    this.update = function (idProducto, producto) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'PUT',
            url: baseUrl + '/api/updateProducto/' + idProducto,
            data: producto
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

    this.delete = function (idProducto) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'DELETE',
            url: baseUrl + '/api/deleteProducto/' + idProducto
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


