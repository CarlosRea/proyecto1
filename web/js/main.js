var app = angular.module("app", ['ngRoute', 'ngCookies']);

app.run(function ($rootScope, $location, $cookieStore) {
    
    $rootScope.$on('$routeChangeStart', function (event, next, current) {
        if ($cookieStore.get('estaConectado') === false || $cookieStore.get('estaConectado') === null || $cookieStore.get('estaConectado') === undefined) {
            if (next.templateUrl === 'temp/login.html' || next.templateUrl !== 'temp/login.html') {
                $location.path('/');
            }
        }
        else {
            var puesto = $cookieStore.get('puesto');

            if (next.templateUrl === 'temp/login.html' || puesto !== "administrador") {
                $location.path('/infoUsuario');
            }
            
        }
    });
});

app.config(['$routeProvider', function ($routeProvider) {

        $routeProvider
                .when('/', {
                    templateUrl: "temp/login.html",
                    controller: "MainController",
                    resolve: {
                        resultado: ['empleadoResource', function (remoteResource) {
                                return remoteResource.iniciarSesion();
                            }]
                    }
                }).when('/infoUsuario', {
                    templateUrl: "temp/infoUsuario.html",
                    controller: "infoUsuarioController"
                })
                .when('/listClientes', {
                    templateUrl: "temp/listadoClientes.html",
                    controller: "listadoClienteController",
                    resolve: {
                        clientes: ['clienteResource', function (remoteResource) {
                                return remoteResource.list();
                            }]
                    }
                })
                .when('/newCliente', {
                    templateUrl: "temp/newCliente.html",
                    controller: "NewClienteController"
                })
                .when('/editCliente/:idCliente', {
                    templateUrl: "temp/newCliente.html",
                    controller: "EditClienteController",
                    resolve: {
                        cliente: ['clienteResource', '$route', function (remoteResource, $route) {
                                return remoteResource.get($route.current.params.idCliente);
                            }],
                        direccion: ['clienteResource', '$route', function (remoteResource, $route) {
                                return remoteResource.getDireccion($route.current.params.idCliente);
                            }],
                        perfil: ['clienteResource', '$route', function (remoteResource, $route) {
                                return remoteResource.getPerfil($route.current.params.idCliente);
                            }]
                    }

                })
                .when('/about', {
                    templateUrl: "temp/about.html",
                    controller: "MainController"
                })
                .when('/listProductos', {
                    templateUrl: "temp/listadoProductos.html",
                    controller: "listadoProductoController",
                    resolve: {
                        productos: ['productoResource', function (remoteResource) {
                                return remoteResource.list();
                            }]
                    }
                })
                .when('/newProducto', {
                    templateUrl: "temp/newProducto.html",
                    controller: "NewProductoController"
                })
                .when('/editProducto/:idProducto', {
                    templateUrl: "temp/newProducto.html",
                    controller: "EditProductoController",
                    resolve: {
                        producto: ['productoResource', '$route', function (remoteResource, $route) {
                                return remoteResource.get($route.current.params.idProducto);
                            }]
                    }

                })
                .when('/newVenta', {
                    templateUrl: "temp/newVentaProducto.html",
                    controller: "NewVentaController",
                    resolve: {
                        clientes: ['clienteResource', function (remoteResource) {
                                return remoteResource.list();
                            }],
                        empleados: ['empleadoResource', function (remoteResource) {
                                return remoteResource.list();
                            }],
                        productos: ['productoResource', function (remoteResource) {
                                return remoteResource.list();
                            }]
                    }
                })
                .when('/listVentas', {
                    templateUrl: "temp/listadoVentas.html",
                    controller: "listadoVentasController",
                    resolve: {
                        ProductosVentas: ['ventaResource', function (remoteResource1) {
                                return remoteResource1.list();
                            }]
                    }
                })
                .when('/editVentaProducto/:idcompra', {
                    templateUrl: "temp/newVentaProducto.html",
                    controller: "EditVentaController",
                    resolve: {
                        venta: ['ventaResource', '$route', function (remoteResource, $route) {
                                return remoteResource.get($route.current.params.idcompra);
                            }],
                        clientes: ['clienteResource', function (remoteResource) {
                                return remoteResource.list();
                            }],
                        empleados: ['empleadoResource', function (remoteResource) {
                                return remoteResource.list();
                            }],
                        productos: ['productoResource', function (remoteResource) {
                                return remoteResource.list();
                            }]
                    }

                })
                .when('/newEmpleado', {
                    templateUrl: "temp/newEmpleado.html",
                    controller: "NewEmpleadoController"
                })
                .when('/listEmpleados', {
                    templateUrl: "temp/listadoEmpleados.html",
                    controller: "listadoEmpleadoController",
                    resolve: {
                        empleados: ['empleadoResource', function (remoteResource) {
                                return remoteResource.list();
                            }]
                    }
                })
                .when('/editEmpleado/:idempleado', {
                    templateUrl: "temp/newEmpleado.html",
                    controller: "EditEmpleadoController",
                    resolve: {
                        empleado: ['empleadoResource', '$route', function (remoteResource, $route) {
                                return remoteResource.get($route.current.params.idempleado);
                            }]
                    }

                })
                .when('/listServicios', {
                    templateUrl: "temp/listadoServicios.html",
                    controller: "listadoOrdenServicioController",
                    resolve: {
                        ordenServicios: ['ordenServicioResource', function (remoteResource) {
                                return remoteResource.list();
                            }]
                    }
                });


    }]);

app.value('usuario',{
                estaConectado: false,
                nombre: "",
                clave: "",
                puesto:""
            });

app.controller("MainController", ['$scope', 'empleadoResource', '$cookieStore', '$location', 'usuario', function ($scope, remoteResource, $cookieStore, $location, usuario) {

        $scope.usuario=usuario;
        if($cookieStore.get('estaConectado')===true){
            usuario.estaConectado=$cookieStore.get('estaConectado');
            usuario.nombre=$cookieStore.get('nombre');
            usuario.puesto=$cookieStore.get('puesto');
            
        }
        
        console.info(usuario);

        $scope.resultado = "";

        $scope.iniciarSesion = function () {
            remoteResource.iniciarSesion(usuario.nombre, usuario.clave).then(function (resultado) {
                               
                $scope.resultado = resultado;
                usrASesion();
                
            });
        };
        
        
        function usrASesion() {
            usuario.estaConectado = true;
            $cookieStore.put('estaConectado', true);
            $cookieStore.put('nombre', $scope.resultado.empleado.nombreEmpleado);
            $cookieStore.put('puesto', $scope.resultado.puesto);
            usuario.puesto=$cookieStore.get('puesto');
            usuario.nombre=$cookieStore.get('nombre');
            usuario.estaConectado=$cookieStore.get('estaConectado');

            $location.path('/infoUsuario');
            console.info(usuario);
        }

    }]);



app.controller('ApCtrl', ['$scope', '$cookieStore','$rootScope', 'usuario',function ($scope, $cookieStore,$rootScope,usuario) {    
        
        $scope.salir = function () {
            $cookieStore.remove('estaConectado');
            $cookieStore.remove('usuario');
            $cookieStore.remove('puesto');
            $cookieStore.remove('clave');
            usuario.puesto=$cookieStore.get('puesto');
            usuario.nombre=$cookieStore.get('usuario');
            usuario.estaConectado=$cookieStore.get('estaConectado');
            usuario.clave=$cookieStore.remove('clave');
        };
        
    }]);

app.controller('infoUsuarioController', ['$scope', '$cookieStore','$rootScope', function ($scope, $cookieStore,$rootScope) {    

    }]);


//Controladores Clientes

app.controller("listadoClienteController", ['$scope', 'clientes' ,'clienteResource', function ($scope, clientes, remoteResource) {
        $scope.clientes = clientes;
        console.info(clientes);
        $scope.borrar = function (idCliente) {
            remoteResource.delete(idCliente).then(function () {
                remoteResource.list().then(function (clientes) {
                    $scope.clientes = clientes;
                });
            });
        };


    }]);

app.controller("NewClienteController", ['$scope', 'clienteResource', '$location', function ($scope, remoteResource, $location) {
        $scope.sex = ["Hombre","Mujer"];

        $scope.perfil = [{
                nombrePerfil: "basico",
                cuota: "20"
            }, {
                nombrePerfil: "premium",
                cuota: "40"
            }];

        $scope.cliente = {
            nombreCliente: "",
            apellidoCliente: "",
            sexo: "",
            fechaNacimiento: "",
            fechaAltaCliente: "",
            telefonoCliente: undefined,
            movilCliente: undefined,
            dni: "",
            direccion: {
                iddireccion: undefined,
                localidad: "",
                calle: "",
                codigoPostal: undefined,
                numero: undefined,
                piso: undefined,
                puerta: undefined
            },
            perfilCliente: {
                nombrePerfil: "",
                cuota: undefined
            }
        };
        $scope.cliente.sexo = $scope.sex[1];
        $scope.cliente.perfilCliente = $scope.perfil[0];
        console.info($scope.cliente);
        $scope.guardar = function () {
            if ($scope.form.$valid) {

                remoteResource.insert($scope.cliente).then(function () {
                    $location.path("/listClientes");
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);

app.controller("EditClienteController", ['$scope', 'cliente', 'direccion', 'perfil', 'clienteResource', '$location', function ($scope, cliente, direccion, perfil1, remoteResource, $location) {

        $scope.sex = ["Hombre","Mujer"];

        $scope.perfil = [{
                nombrePerfil: "basico",
                cuota: "20"
            }, {
                nombrePerfil: "premium",
                cuota: "40"
            }];

        $scope.cliente = cliente;
        console.info(cliente);
        $scope.cliente.direccion = direccion;
        $scope.cliente.perfilCliente = perfil1;
        
        if (cliente.sexo === "Hombre")
            $scope.cliente.sexo = $scope.sex[0];
        if (cliente.sexo === "Mujer")
            $scope.cliente.sexo = $scope.sex[1];

        if (cliente.fechaNacimiento !== null) {
            $scope.cliente.fechaNacimiento = new Date(cliente.fechaNacimiento);
        }
        if (cliente.fechaAltaCliente !== null) {
            $scope.cliente.fechaAltaCliente = new Date(cliente.fechaAltaCliente);
        }

        if (cliente.perfilCliente.nombrePerfil === "basico")
            $scope.cliente.perfilCliente = $scope.perfil[0];
        if (cliente.perfilCliente.nombrePerfil === "premium")
            $scope.cliente.perfilCliente = $scope.perfil[1];

        $scope.guardar = function () {
            if ($scope.form.$valid) {
                remoteResource.update($scope.cliente.idcliente, $scope.cliente).then(function () {
                    $location.path("/listClientes");
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);


//controladores Productos
app.controller("listadoProductoController", ['$scope', 'productos', 'productoResource', function ($scope, productos, remoteResource1) {
        $scope.productos = productos;
        $scope.borrar = function (idProducto) {
            remoteResource1.delete(idProducto).then(function () {
                remoteResource1.list().then(function (productos) {
                    $scope.productos = productos;
                });
            });
        };

    }]);

app.controller("NewProductoController", ['$scope', 'productoResource', '$location', function ($scope, remoteResource, $location) {

        $scope.categoria = [{
                nombreCategoria: "champus"
            }, {
                nombreCategoria: "gel"
            }];


        $scope.producto = {
            idproducto: undefined,
            nombreProducto: "",
            capacidadProducto: "",
            precioCompra: "",
            precioVenta: "",
            precioServicio: "",
            barCodigo: "",
            categoriaProducto: {
                nombreCategoria: ""
            }
        };

        $scope.producto.categoriaProducto = $scope.categoria[0];
        $scope.guardar = function () {
            if ($scope.form.$valid) {

                remoteResource.insert($scope.producto).then(function () {
                    $location.path("/listProductos");
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);


app.controller("EditProductoController", ['$scope', 'producto', 'productoResource', '$location', function ($scope, producto, remoteResource, $location) {

        $scope.categoria = [{
                nombreCategoria: "champus"
            }, {
                nombreCategoria: "gel"
            }];

        $scope.producto = producto;
        console.info(producto);
        if (producto.categoriaProducto === "champus")
            $scope.producto.categoriaProducto = $scope.categoria[0];
        if (producto.categoriaProducto === "gel")
            $scope.producto.categoriaProducto = $scope.categoria[1];

        $scope.guardar = function () {
            if ($scope.form.$valid) {
                remoteResource.update($scope.producto.idproducto, $scope.producto).then(function () {
                    $location.path("/listProductos");
                }, function (bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);

// Controladores ventas 

app.controller("NewVentaController", ['$scope', 'ventaResource', 'clientes', 'empleados', 'productos', '$location', function ($scope, remoteResource, clientes, empleados, productos, $location) {

        $scope.venta = {
            idcompra: undefined,
            empleados: undefined,
            clientes: undefined,
            productos: undefined,
            fechaCompra: "",
            comisionEmpleado: undefined,
            facturado: "",
            cantidad: undefined
        };

        $scope.clientes = clientes;
        $scope.empleados = empleados;
        $scope.productos = productos;

        $scope.venta.clientes = clientes[0].cliente;
        $scope.venta.empleados = empleados[0].empleado;
        $scope.venta.productos = productos[0];
        console.info(productos);

        $scope.guardar = function () {
            if ($scope.form.$valid) {
                remoteResource.insert($scope.venta).then(function () {
                    $location.path("/listVentas");
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);

app.controller("listadoVentasController", ['$scope', 'ProductosVentas', 'ventaResource', function ($scope, ProductosVentas, remoteResource1) {
        $scope.ProductosVentas = ProductosVentas;
        console.info($scope.ProductosVentas);
        $scope.borrar = function (idcompra) {
            remoteResource1.delete(idcompra).then(function () {
                remoteResource1.list().then(function (productos) {
                    $scope.productos = productos;
                });
            });
        };

    }]);

app.controller("EditVentaController", ['$scope', 'venta', 'productoResource', 'clientes', 'empleados', 'productos', '$location', function ($scope, venta, remoteResource, clientes, empleados, productos, $location) {

        $scope.venta = venta;

        if (venta.fechaCompra !== null)
            $scope.venta.fechaCompra = new Date(venta.fechaCompra);


        $scope.clientes = clientes;
        $scope.empleados = empleados;
        $scope.productos = productos;


        $scope.guardar = function () {
            if ($scope.form.$valid) {
                remoteResource.update($scope.venta.idcompra, $scope.venta).then(function () {
                    $location.path("/listVentas");
                }, function (bussinessMessages) {
                    $scope.bussinessMessages = bussinessMessages;
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);

// controladores Empleados

app.controller("listadoEmpleadoController", ['$scope', 'empleados', 'empleadoResource', function ($scope, empleados, remoteResource) {
        $scope.empleados = empleados;

        $scope.borrar = function (idempleado) {
            remoteResource.delete(idempleado).then(function () {
                remoteResource.list().then(function (empleados) {
                    $scope.empleados = empleados;
                });
            });
        };

    }]);

app.controller("NewEmpleadoController", ['$scope', 'empleadoResource', '$location', function ($scope, remoteResource, $location) {
        $scope.sex = [{
                codSexo: "H",
                descripcion: "Hombre"
            }, {
                codSexo: "M",
                descripcion: "Mujer"
            }];

        $scope.puesto = [{
                nombrePuesto: "peluqersd"
            }, {
                nombrePuesto: "masasjd"
            }];

        $scope.empleado = {
            nombreEmpleado: "",
            apellidoEmpleado: "",
            dni: "",
            sexo: "",
            fechaNacimiento: "",
            fechaAltaEmpleado: "",
            telefonoEmpleado: undefined,
            movilEmpleado: undefined,
            direccion: {
                iddireccion: undefined,
                localidad: "",
                calle: "",
                codigoPostal: undefined,
                numero: undefined,
                piso: undefined,
                puerta: undefined
            },
            puestoempleado: {
                nombrePuesto: ""
            }
        };
        $scope.empleado.sexo = $scope.sex[1].codSexo;
        $scope.empleado.puestoempleado = $scope.puesto[0];
        $scope.guardar = function () {
            if ($scope.form.$valid) {
                remoteResource.insert($scope.empleado).then(function () {
                    $location.path("/listEmpleados");
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);

app.controller("EditEmpleadoController", ['$scope', 'empleado', 'empleadoResource', '$location', function ($scope, empleado, remoteResource, $location) {

        $scope.sex = [{
                codSexo: "H",
                descripcion: "Hombre"
            }, {
                codSexo: "M",
                descripcion: "Mujer"
            }];
        $scope.puesto = [{
                nombrePuesto: "peluqersd"
            }, {
                nombrePuesto: "masasjd"
            }];


        $scope.empleado = empleado.empleado;
        $scope.empleado.direccion = empleado.direccion;

        if (empleado.fechaNacimiento !== null) {
            $scope.empleado.fechaNacimiento = new Date(empleado.empleado.fechaNacimiento);
        }
        if (empleado.fechaAltaEmpleado !== null) {
            $scope.empleado.fechaAltaEmpleado = new Date(empleado.empleado.fechaAltaEmpleado);
        }

        if (empleado.empleado.sexo === "Hombre")
            $scope.empleado.sexo = $scope.sex[0].codSexo;
        if (empleado.empleado.sexo === "Mujer")
            $scope.empleado.sexo = $scope.sex[1].codSexo;

        if (empleado.puestoempleado === "peluqersd")
            $scope.empleado.puestoempleado = $scope.puesto[0];
        if (empleado.puestoempleado === "masasjd")
            $scope.empleado.puestoempleado = $scope.puesto[1];


        $scope.guardar = function () {
            if ($scope.form.$valid) {
                remoteResource.update($scope.empleado.idempleado, $scope.empleado).then(function () {
                    $location.path("/listEmpleados");
                });
            } else {
                alert("Hay datos inválidos");
            }
        };

    }]);

// controladores ordenServicio

app.controller("listadoOrdenServicioController", ['$scope', 'ordenServicios', 'ordenServicioResource', function ($scope, ordenServicios, remoteResource) {
        $scope.ordenServicios = ordenServicios;
        

        $scope.borrar = function (idempleado) {
            remoteResource.delete(idempleado).then(function () {
                remoteResource.list().then(function (empleados) {
                    $scope.empleados = empleados;
                });
            });
        };

    }]);




//provider Clientes

function clienteResourceProvider() {
    var _baseUrl;
    this.setBaseUrl = function (baseUrl) {
        _baseUrl = baseUrl;
    };
    this.$get = ['$http', '$q', function ($http, $q) {
            return new RemoteResourceCliente($http, $q, _baseUrl);
        }];
}
;

//provider productos
function productoResourceProvider() {
    var _baseUrl;
    this.setBaseUrl = function (baseUrl) {
        _baseUrl = baseUrl;
    };
    this.$get = ['$http', '$q', function ($http, $q) {
            return new RemoteResourceProducto($http, $q, _baseUrl);
        }];
}
;

//provider ventas
function ventaResourceProvider() {
    var _baseUrl;
    this.setBaseUrl = function (baseUrl) {
        _baseUrl = baseUrl;
    };
    this.$get = ['$http', '$q', function ($http, $q) {
            return new RemoteResourceVenta($http, $q, _baseUrl);
        }];
}
;

//provider empleados
function empleadoResourceProvider() {
    var _baseUrl;
    this.setBaseUrl = function (baseUrl) {
        _baseUrl = baseUrl;
    };
    this.$get = ['$http', '$q', function ($http, $q) {
            return new RemoteResourceEmpleado($http, $q, _baseUrl);
        }];
}
;

//provider ordenservicio
function ordenServicioResourceProvider() {
    var _baseUrl;
    this.setBaseUrl = function (baseUrl) {
        _baseUrl = baseUrl;
    };
    this.$get = ['$http', '$q', function ($http, $q) {
            return new RemoteResourceOrdenServicio($http, $q, _baseUrl);
        }];
}
;

app.provider("productoResource", productoResourceProvider);
app.provider("clienteResource", clienteResourceProvider);
app.provider("ventaResource", ventaResourceProvider);
app.provider("empleadoResource", empleadoResourceProvider);
app.provider("ordenServicioResource", ordenServicioResourceProvider);

app.constant("baseUrl", ".");
app.config(['baseUrl', 'clienteResourceProvider', 'productoResourceProvider', 'ventaResourceProvider', 'empleadoResourceProvider','ordenServicioResourceProvider',
    function (baseUrl, clienteResourceProvider, productoResourceProvider, ventaResourceProvider, empleadoResourceProvider,ordenServicioResourceProvider) {
        clienteResourceProvider.setBaseUrl(baseUrl);
        productoResourceProvider.setBaseUrl(baseUrl);
        ventaResourceProvider.setBaseUrl(baseUrl);
        empleadoResourceProvider.setBaseUrl(baseUrl);
        ordenServicioResourceProvider.setBaseUrl(baseUrl);
    }
]);

//provider Productos