/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function RemoteResourceEmpleado($http, $q, baseUrl) {
    
    this.iniciarSesion = function (nombreUsuario,clave) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/iniciarSesion/' + nombreUsuario+'/'+clave
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
    
    this.get = function (idempleado) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'GET',
            url: baseUrl + '/api/getEmpleado/' + idempleado
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
            url: baseUrl + '/api/ListaEmpleados'
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

    this.insert = function (empleado) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'POST',
            url: baseUrl + '/api/insertEmpleado',
            data: empleado
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

    this.update = function (idempleado, empleado) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'PUT',
            url: baseUrl + '/api/updateEmpleado/' + idempleado,
            data: empleado
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

    this.delete = function (idempleado) {
        var defered = $q.defer();
        var promise = defered.promise;

        $http({
            method: 'DELETE',
            url: baseUrl + '/api/deleteEmpleado/' + idempleado
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




