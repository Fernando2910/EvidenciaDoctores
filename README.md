# Gestión de Citas Médicas - Documentación

Este proyecto consiste en una aplicación de gestión de citas médicas desarrollada en Java. Proporciona funcionalidades para agregar doctores, pacientes, crear citas médicas, gestionar usuarios y realizar el inicio de sesión. A continuación se detalla la documentación de las clases y métodos implementados en el código.

## Estructura del Proyecto

El proyecto está organizado en varios paquetes:

1. `Conexion`: Contiene la clase `CConexion` que se encarga de establecer la conexión con la base de datos MySQL.
2. `Consultas`: Contiene la clase `Consultas` que maneja todas las consultas SQL y métodos relacionados con la base de datos.

## Clase `CConexion` (Paquete Conexion)

Esta clase se encarga de establecer la conexión con la base de datos MySQL. Define la URL de conexión, el usuario y la contraseña necesarios para la conexión.

## Clase `Consultas` (Paquete Consultas)

### Métodos de Consulta de Usuario

1. `consultarUsuario(String user, String pass)`: Verifica las credenciales de inicio de sesión de un usuario en la base de datos. Retorna un valor booleano indicando si el inicio de sesión fue exitoso o no.

### Métodos de Gestión de Usuarios

2. `AgregarUsuario(String nombre_usuario, String contrasena, String tipo_usuario)`: Agrega un nuevo usuario a la base de datos con el nombre de usuario, contraseña y tipo de usuario proporcionados.

### Métodos de Gestión de Doctores

3. `AgregarDoctor(String nombre, String especialidad)`: Permite agregar un nuevo doctor a la base de datos con el nombre y la especialidad proporcionados.

4. `MostrarDoctores(JTable jtableListaDoctores)`: Recupera todos los doctores registrados en la base de datos y los muestra en una tabla de la interfaz de usuario.

5. `SeleccionarDoctor(JTable jtableListaDoctores, JTextField id, JTextField Nombre, JTextField Especialidad)`: Permite seleccionar un doctor de la tabla y mostrar sus detalles en campos de texto para su posterior modificación.

6. `ActualizarDoctor(String id, String Nombre, String Especialidad)`: Actualiza la información de un doctor en la base de datos según los datos proporcionados.

### Métodos de Gestión de Pacientes

7. `AgregarPaciente(String nombre)`: Añade un nuevo paciente a la base de datos con el nombre proporcionado.

8. `MostrarPaciente(JTable jtableListaPaciente)`: Recupera todos los pacientes registrados en la base de datos y los muestra en una tabla de la interfaz de usuario.

9. `SeleccionarPaciente2(JTable jtableListaPaciente, JTextField id, JTextField Nombre)`: Permite seleccionar un paciente de la tabla y mostrar sus detalles en campos de texto para su posterior modificación.

10. `ActualizarPaciente(String id, String Nombre)`: Actualiza la información de un paciente en la base de datos según los datos proporcionados.

### Métodos de Gestión de Citas Médicas

11. `VerificarDisponibilidad(String fecha, String hora)`: Verifica si la fecha y hora seleccionadas para una cita están disponibles en la base de datos.

12. `InsertarCita(String fecha, String hora, String descripcion, String id_doctor, String id_Paciente)`: Inserta una nueva cita en la base de datos con la fecha, hora, descripción, ID del doctor y ID del paciente proporcionados.

13. `MostrarCita(JTable jtableListaPaciente)`: Recupera todas las citas médicas registradas en la base de datos y las muestra en una tabla de la interfaz de usuario.

14. `SeleccionarCita(JTable jtableListaCitas, JTextField idCita, JDateChooser dateChooser, JComboBox comboBoxHora, JTextField fieldDescripcion, JComboBox comboBoxIdDoctor, JComboBox comboBoxIdPaciente)`: Permite seleccionar una cita de la tabla y mostrar sus detalles en los campos correspondientes de la interfaz de usuario para su posterior modificación.

Para obtener detalles específicos sobre los parámetros y el funcionamiento de cada método, se recomienda revisar el código fuente.

¡Gracias por utilizar nuestra aplicación de gestión de citas médicas!

Derechos reservados por Fernando Hernández.
Estudiante de Tecmilenio campus Zapopan.
