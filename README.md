# Prueba-Microformas

📱 App de Gestión de Servicios

⸻

📝 Descripción

Aplicación Android desarrollada con Jetpack Compose, diseñada para consultar y mostrar una lista de servicios desde un API REST, con persistencia local y gestión segura de tokens.

Características principales:
 - Consumo de API con Retrofit
 - Persistencia local con Room y DataStore
 - Gestión de dependencias con Hilt
 - Arquitectura limpia: Repository + ViewModel + UI
 - Manejo de estados: Loading, Success, Error
 - UI adaptativa con Jetpack Compose

⸻

⚙ Tecnologías usadas
 - Kotlin
 - Jetpack Compose
 - Retrofit + Gson
 - Room (base de datos local)
 - DataStore (Preferences)
 - Hilt (inyección de dependencias)
 - Coroutines / Flow
 - Tink (para almacenamiento seguro de datos)

⸻

📂 Estructura del proyecto

```
app/
├─ data/
│  ├─ database/          # Room (DAO, Database, Entity, etc)
│  ├─ api/               # API interfaces (Retrofit)
│  ├─ di/                # Inyección de dependencias del package data (api, database, dataStore, repository)
│  └─ repository/        # Implementación del repository (auth, services)
├─ di/                   # Inyeción de una dependencia general para toda la app (Dispatchers.IO)
├─ domain/
│  ├─ dataStore+Tink/    # Implementación del guardado de un (key - value) con dataStore y el cifrado tink
│  ├─ interfaces/        # Interfaces (AuthRepository, ServiceRepository, LocalDataSource)
│  └─ service            # Modelo Service
├─ navigation/           # Clase encargada de la navegación de pantallas (NavHost)
├─ presentation/
│  └─ screens/
│     ├─ login/          # Pantalla de Login con su state, actions, events y viewModel
│     └─ services/       # Pantalla de Services (listado de servicios) con su state, actions, events y viewModel
├─ ui/
│  └─ theme/             # Config del theme por default (autogenerado por el IDE)
├─ Utils/                # Algunas constantes
├─ MainActivity          # @AndroidEntryPoint
└─ TestApplication       # @HiltAndroidApp
```

Obvio hay y puede haber mejores formas de acomodar/nombrar los packages y clases, yo sin problemas puedo seguir lineamientos
