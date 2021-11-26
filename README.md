# RealState con seguridad

#### Repositorio del proyecto **REALSTATE** del módulo de Acso a Datos y Programación de Servicios y Procesos 2021-22. ####

</br>

### Descargar JDK 17 de la página de Oracle, y también descargar IntelliJ IDEA Community Edition. Para poner en marcha el proyecto, una vez se ha abierto en *IntelliJ IDEA*, es ir a la pestaña de Maven, a la derecha de la ventana, abrir la carpeta de Plugins, abrir el apartado de *spring-boot* y pulsar donde diga *spring-boot:run*
### La aplicación nos permite manejar todas las funcionalidades de una página de Venta/Alquiler/Obra Nueva de *Viviendas*(`Vivienda`), en las cuales podemos consultar si dichas *Viviendas* son ofrecidas por un *Propietario*(`Propietario`) común, o si además están controladas por alguna *Inmobiliaria*(`Inmobiliaria`). Además, cuenta con la función de ver la cantidad de *Interesados*(`Interesado`) que tiene una vivienda en particular. 
</br>


## Entidades de la aplicación:
* ### Inmobiliaria
* ### Vivienda
* ### Usuario
* ### Interesa


  
</br>

## <p>Funcionalidades de Inmobiliaria:</p> ##
#### Asociación @OneToMany hacia Vivienda
#### Asociación @OneToMany hacia Usuario
* Crear nueva Inmobiliaria, usuario Admin
* Obtener todas la Inmobiliaria, cualquier usuario autenticado
* Obtener los datos de una Inmobiliaria concreta, cualquier usuario autenticado
* Borrar una Inmobiliaria concreta, cualquier usuario Admin
* Añadir un usuario gestor a la inmobiliaria, usuario Admin
* Elimina un usuario gestor de una inmobiliaria, otro gestor de dicha inmobiliaria o Admin
* Obtiene todos los gestores de una inmobiliaria, otro gestor de la dicha inmobiliaria o Admin
</br>

## <p>Funcionalidades de Vivienda:</p> ##

#### Asociación @ManyToOne hacia Inmobiliaria 
#### Asociación @ManyToOne hacia Usuario
#### Asocioación @OneToMany hacia Interesa
* Crear una nueva vivienda, dando de alta un propietario a la vez, solo usuario propietario
* Mostrar todas las viviendas existentes, cualquier usuario autenticado
* Ver los datos de una vivienda en concreto, cualquier usuario autenticado
* Modificar los datos de una vivienda, solamente usuario de dicha vivienda o Admin
* Borrar una vivienda, usuario de dicha vivienda, Admin, o gestor de esa inmobiliaria
* Hacer que una vivienda quede gestionada por una inmobiliaria concreta, solamente usuario propietario de dicha vivienda o Admin
* Elimina la gestión de una vivienda por parte de una inmobiliaria

</br>

## <p>Funcionalidades de Usuario:</p> ##

#### Asociación @ManyToOne hacia Inmobiliaria
#### Asociación @MOneToMany hacia Vivienda
#### Asociación @MOneToMany hacia Interesa
* Dar de alta un usuario propietario
* Login de un usuario
* Dar de alta un usuario gestor de una inmobiliaria, solo lo puede realizar Admin
* Dar de alta un administrador, solo lo puede realizar Admin
* Obtener todos los propietarios, cualquier usuario autenticado
* Obtener los datos de un propietario, solamente lo puede realizar el propietario que se va a consultar o Admin
* Eliminar un propietario, solamente lo puede realizar un propietario que se va a eliminar o Admin
</br>

## <p>Funcionalidades de Interesa:</p> ##

#### @ManyToOne hacia Vivienda
#### @ManyToOne hacia Usuario


* Crear un nuevo interesado, que se interese por una vivienda concreta, solamente lo puede realizar un Propietario
* Eliminar el interés de un interesado por una vivienda concreta, solamente lo puede realizar el usuario en cuestión o un Admin
* Obtener todos los Interesados, solamente lo puede realizar un Admin
* Mostrar todos los datos de un interesado, solamente lo puede realizar un Admin
* Obtener las 10 viviendas por las que se hayan interesado, cualquier usuario autenticado
</br> 


## Realizado por:
* ### Jesús Barco Espinar
