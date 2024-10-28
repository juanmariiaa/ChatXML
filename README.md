# Aplicación de Mensajería Instantánea

Este proyecto es una **Aplicación de Mensajería Sincronizada** desarrollado como parte de la asignatura de **Acceso a Datos**. La aplicación permite a dos enviar y recibir mensajes entre sí dentro del mismo sistema. Los mensajes se almacenan en un archivo XML y se actualizan automáticamente en las interfaces gráficas de los usuarios.

## Tecnologías Utilizadas

- **Java:** El lenguaje de programación utilizado para desarrollar la lógica de la aplicación, empleando el paradigma orientado a objetos y el patrón de diseño **MVC** (Modelo-Vista-Controlador).

- **JavaFX:** Framework de Java para crear interfaces gráficas de usuario. Se utilizó para todas las ventanas que se muestran en el programa.

- **JAXB:** Librería de Java para trabajar con archivos XML. Se utilizó para crear las clases que representan los elementos del archivo XML donde se guardan los mensajes, usuarios, etc. así como los métodos para leer y escribir en dicho archivo.


## Funcionamiento de la Aplicación

1. **Inicio de Sesión:** Al iniciar la aplicación, se solicita al usuario que introduzca su nombre. Si el usuario no tiene una cuenta, puede registrarse fácilmente desde esta pantalla.

2. **Pantalla de Bienvenida:** Una vez que el usuario inicia sesión o se registra, se presenta una pantalla de inicio que le da la bienvenida. Desde aquí, el usuario puede navegar a dos lugares principales: la pantalla de chat o su perfil.

3. **Pantalla Principal de Chat:** Esta es la sección más activa de la aplicación, donde los usuarios pueden:
    - **Añadir Amigos:** Los usuarios pueden agregar amigos a su lista de contactos.
    - **Enviar Mensajes:** Los usuarios pueden escribir y enviar mensajes a sus amigos.
    - **Exportar Conversaciones:** Las conversaciones seleccionadas se pueden exportar a archivos `.txt` para su almacenamiento o análisis fuera de la aplicación.
    - **Análisis de Conversaciones:** Se ofrece la posibilidad de realizar un análisis de las conversaciones utilizando **Java Streams**, lo que permite procesar y obtener información relevante de los mensajes.

4. **Interfaz Intuitiva:** La aplicación cuenta con una interfaz gráfica bien conectada e intuitiva, lo que facilita la navegación y mejora la experiencia del usuario al interactuar con las diversas funciones disponibles.

5. **Actualización de Mensajes:** Al enviar o recibir mensajes, estos se almacenan en un archivo XML. La interfaz gráfica se actualiza automáticamente para reflejar los nuevos mensajes en tiempo real.

## Diagrama de Clases

![Diagrama de Clases](ruta/del/diagrama.png) <!-- Reemplaza esta línea con la ruta a tu diagrama de clases -->

## Instalación

Para instalar y ejecutar la aplicación, sigue estos pasos:

1. **Descarga:** Descarga el archivo `.rar` que estará disponible en la sección de **Releases**.

2. **Ejecuta el archivo:** Ejecuta el archivo `Execute.bat` para iniciar la aplicación.

3. **¡Disfruta!**

## Conclusiones

Esta aplicación es un ejemplo de cómo utilizar Java, JavaFX y JAXB para crear una aplicación de mensajería sincronizada, cumpliendo con los requisitos de la asignatura de **Acceso a Datos** en un tiempo estimado de dos semanas. Espero que este proyecto sirva de inspiración para otros desarrolladores y estudiantes.

