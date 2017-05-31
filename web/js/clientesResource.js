/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function RemoteResourceCliente($http, $q, baseUrl) {
    this.get = function (idCliente) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/getCliente/' + idCliente
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
    
    this.getDireccion = function (idCliente) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/getDireccion/' + idCliente
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
    
    this.getPerfil = function (idCliente) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/getPerfil/' + idCliente
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
            url: baseUrl + '/api/ListaClientes'
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

    this.insert = function (cliente) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'POST',
            url: baseUrl + '/api/insertCliente',
            data: cliente
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

    this.update = function (idCliente, cliente) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'PUT',
            url: baseUrl + '/api/updateCliente/' + idCliente,
            data: cliente
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

    this.delete = function (idCliente) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'DELETE',
            url: baseUrl + '/api/deleteCliente/' + idCliente
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


