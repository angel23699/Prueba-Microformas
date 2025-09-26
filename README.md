# Prueba-Microformas

📱 App de Gestión de Servicios


⸻

📝 Descripción

Aplicación Android desarrollada con Jetpack Compose, diseñada para consultar y mostrar una lista de servicios desde un API REST, con persistencia local y gestión segura de tokens.

Características principales:
	•	Consumo de API con Retrofit
	•	Persistencia local con Room y DataStore
	•	Gestión de dependencias con Hilt
	•	Arquitectura limpia: Repository + ViewModel + UI
	•	Manejo de estados: Loading, Success, Error
	•	UI adaptativa con Jetpack Compose

⸻

⚙ Tecnologías usadas
	•	Kotlin
	•	Jetpack Compose
	•	Retrofit + Gson
	•	Room (base de datos local)
	•	DataStore (Preferences)
	•	Hilt (inyección de dependencias)
	•	Coroutines / Flow
	•	Tink (opcional, para almacenamiento seguro de datos)

⸻

📂 Estructura del proyecto

app/
├─ data/
│  ├─ local/          # Room / DataStore
│  ├─ remote/         # API interfaces (Retrofit)
│  └─ repository/     # Repository e implementación
├─ di/                # Módulos Hilt
├─ ui/
│  ├─ screens/        # Pantallas Compose
│  └─ components/     # Composables reutilizables
├─ utils/             # Constantes, mappers, helpers
└─ viewmodel/         # ViewModels


⸻

🚀 Instalación / Setup
	1.	Clonar el repositorio:

git clone https://github.com
