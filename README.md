# SendMessageBinding

## Para que sirve
En este proyecto se envía un objeto Message (Datos de usuario más mensaje) entre Activities.


## Conceptos aprendidos
### Crear JavaDoc
Con este proyecto hemos aprendido a generar un JavaDoc, para ello añadimos las siguientes líneas en el apartado *'dependencies'* del fichero 'build.gradle' del módulo.

	implementation fileTree(include: ['*.jar'], dir: 'libs')
	implementation files('C:/Users/Usuario/AppData/Local/Android/Sdk/platforms/android-31/android.jar')

Y seleccionando la opción *Tools > Generate JavaDoc...* se creará el documento.

------------

### Asignar un OnClickListener
También asignamos un método que se ejecutará al clickar un botón mediante una expresión Lambda.

	binding.btSend.setOnClickListener(view -> sendMessage());

------------

### Ciclo de vida de una Activity
Las activities tienen varios métodos que reflejan sus estados en su ciclo de vida:
+ OnCreate(): Se ejecuta cuando se crea la Activity.
+ OnStart(): Da comienzo al ciclo de vida que es visible para el usuario.
+ OnResume(): Se ejecuta cuando la aplicación adquiere el primer plano.
+ OnPause(): Se ejecuta cuando la Activity deja de estar en primer plano. (Par con OnResume())
+ OnStop(): Se ejecuta cuando la Activity deja de ser visible para el usuario. (Par con onStart())
+ OnDestroy(): Se ejecuta cuando se elimina la Activity. (Par con OnCreate())

Las variables que sean inicializadas en OnCreate(), OnStart() y OnResume(), deberán destruirse en sus métodos pares.

------------

### Pasar datos entre Activities
Gracias al DataBinding, creamos un dato Message en las Activities del programa que nos permitía en los atributos *text* de los TextView/EditText vincularlos al getter y/o setter de una de las variables, ejemplo:

![capturaDataXML](https://user-images.githubusercontent.com/113918779/194163506-57fc08bc-49e7-4341-a48b-0c9243455918.PNG)


Para pasar el objeto Message entre Activities hicimos uso de las clases Bundle (Donde añadíamos el objeto Message mediante *putParcelable()* con su key y value correspondiente) e Intent (Donde pasamos el contexto de la Activity y la clase de la Activity a abrir, y añadíamos el bundle mediante el método *putExtras()*) para por último iniciar la Activity:

	Bundle bundle = new Bundle();
	bundle.putParcelable("message", binding.getMessage());
	Intent intent = new Intent(this, ViewMessageActivity.class);
	intent.putExtras(bundle);
	startActivity(intent);

Por último, para mostrar el objeto, había que leer el intent en la clase de la Activity destino:

	Bundle bundle = getIntent().getExtras();
	Message message = bundle.getParcelable("message");
	binding.setMessage(message);

------------

### Añadir un icono a la aplicación
Para añadir una imagen como icono de la aplicación, en Android Studio (Con vista *Project*) hicimos click derecho en el fichero app y seleccionamos la opción '*New > Image Asset*'

![imageAsset](https://user-images.githubusercontent.com/113918779/194235343-d7946354-dd1e-45f6-9476-2ce14118caa3.PNG)

En esa ventana podremos configurar el icono de nuestra aplicación, cambiando la imagen, el color de fondo, la forma del fondo... y nos creará los ficheros necesarios para llevar a cabo el cambio.

------------

### Añadir imagen al layout
Añadiendo una vista ImageView a nuestro layout, podremos añadir una imagen a este. Los ImageView cuentan con un atributo '*srcCompat*' que debe tener como valor la referecncia a la imagen, que estará ubicada en la carpeta *drawable*.

	<ImageView
            android:id="@+id/imgMessage"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:srcCompat="@drawable/sobre"
            android:contentDescription="@string/imgMessageContentDescription" />

------------

### Ficheros con traducciones
Para dotar a nuestra aplicación de poder ser usada por gente que hable un idioma distinto al nuestro, tendremos que crear en la herramienta *Translation Editor* de Android Studio diferentes recursos orientados a un lenguaje.

![image](https://user-images.githubusercontent.com/113918779/194239859-63ef49cc-8b66-4780-9faf-b49c5f54e1c1.png)

Desde esta herramienta podremos añadir el idioma que queramos y esta se encargará de crear los recursos necesarios, solo tendremos que rellenar la tabla con las traducciones correspondientes y nuestra aplicación se encargará de usar el idioma correspondiente dependiendo del idioma del sistema del móvil (En caso de que el móvil no cuente con ningún lenguaje de los que contempla nuestra aplicación, usará el que tenga por defecto).

**Nota:** Si existe alguna variable cuya traducción no sea necesaria, podremos marcar con un tick su columna *Untranslatable* y usará el valor por defecto siempre.


------------

### Interfaz Parcelable
Para pasar nuestro objeto *Message* entre Activities de manera más eficiente, en nuestra clase implementamos la interfaz '*Parcelable*':

	public class Message implements Serializable, Parcelable {
	...
	}

Para que haga su función, haremos click derecho en la interfaz y seleccionaremos '*Show Context Actions*' y eligiremos la opción '*Add Parcelable Implementation*', esto nos creará los métodos necesarios para su funcionalidad.

------------

### Añadir librería externa
A través de la opción *File > Project Structure > Dependencies > Add Dependecy > Library Dependecy* añadimos la librería [Logger](https://github.com/orhanobut/logger "Logger") del usuario @orhanobut de GitHub. El proyecto también cuenta con la librería [MaterialAbout](https://github.com/jrvansuita/MaterialAbout) de @jrvansuita, con la que se diseña una Activity 'About Us'.

------------

### Añadir menú a la aplicación
Para añadir un menú nuestra aplicación, primero tuvimos que hacer click derecho en la carpeta 'res' del proyecto (Con vista *Android*) y seleccionar *New > Android Resource Directory*, se nos abrirá una ventana en la que especificaremos en el 'Resource type' que será de tipo menu, en la carpeta que crea, añadiremos un 'Menu Resource File'. Contenido del fichero:

	<?xml version="1.0" encoding="utf-8"?>
	<menu xmlns:android="http://schemas.android.com/apk/res/android">
		<item
		   android:id="@+id/action_aboutus"
		   android:title="@string/aboutUsTitle" />
	</menu>

Para que el menú sea visible en nuestra Activity, reescribimos los métodos *OnCreateOptionsMenu()* y *onOptionsItemSelected()*.
Código del *OnCreateOptionsMenu()* (Añade el menú a la Activity):

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

Código del *onOptionsItemSelected()* (Abre una Activity con el contenido de 'Sobre nosotros'):

	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_aboutus:
				Intent intent = new Intent(this, AboutUsActivity.class);
				startActivity(intent);
				break;
		}
	return true;
	}

## Características
- SDK mínimo para el uso de la aplicación: 23
- SDK objetivo de la aplicación: 32
- SDK del compilado de la aplicación: 32
- Versión: 1.0


## Librerías
El proyecto cuenta con las librerías por defecto de Android, además de las externas de la API 32 de Android junto a la librería [Logger](https://github.com/orhanobut/logger "Logger") y [MaterialAbout](https://github.com/jrvansuita/MaterialAbout)
