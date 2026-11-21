# WalletView 
Dashboard financiero académico hecho con Kotlin y Jetpack Compose.

## Requisitos
- Android Studio
- JDK 17
- Android SDK Platform 35
- Emulador o dispositivo Android API 23+

## Ejecutar
1. Descomprime el ZIP.
2. Abre Android Studio y selecciona **Open**.
3. Selecciona la carpeta WalletView.
4. Espera a que termine Gradle Sync.
5. Ejecuta la configuración `app`.

## Funciones
- Total de gastos de junio.
- Gráfico circular por categoría.
- Gráfico de barras de seis meses.
- Datos ficticios, sin conexión bancaria.

## Toolkit
Jetpack Compose Material 3 para la interfaz y Compose Canvas para dibujar los gráficos. Se evita una dependencia externa de gráficas para reducir problemas de compatibilidad.
