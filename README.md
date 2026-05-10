# ai-productivity-coach
Habit tracker con análisis de productividad y coaching inteligente
Objetivo
Crear una aplicación que transforme hábitos en insights útiles para mejorar la productividad personal mediante análisis contextual y recomendaciones inteligentes.

AI Productivity Coach es una aplicación móvil Android diseñada para ayudar a los usuarios a gestionar hábitos diarios, analizar su productividad y recibir recomendaciones personalizadas tipo “coach”.

A diferencia de un habit tracker tradicional, esta aplicación incorpora contexto del usuario (como días con imprevistos) para evitar interpretaciones injustas del rendimiento. Además, genera insights automáticos basados en patrones de comportamiento.

El objetivo del sistema es transformar datos de hábitos en información útil que permita mejorar la productividad personal de forma realista y sostenible.

Problema que resuelve
Las aplicaciones actuales de hábitos presentan varias limitaciones:
No consideran el contexto real del usuario.
Penalizan todos los días por igual.
Ofrecen recomendaciones genéricas.
No explican el “por qué” del comportamiento del usuario.

Esto genera frustración y abandono de la app.

Plataforma
Sistema operativo: Android
Lenguaje: Kotlin
Entorno de desarrollo: Android Studio
Base de datos: Firebase o SQLite

Funcionalidades
Gestión de hábitos
Crear, editar y eliminar hábitos
Clasificación por categorías

Registro diario
✔ Cumplido
✘ No cumplido
⚠ Día con imprevistos (no afecta KPIs)

KPIs personales
Cumplimiento semanal
Streaks
Tendencias de productividad

Coach inteligente
Detección de patrones
Recomendaciones automáticas
Sugerencias editables por el usuario

IA (fase 2)
Resúmenes semanales
Explicaciones en lenguaje natural

Interfaz de usuario
Login
Dashboard de KPIs
Lista de hábitos
Check-in diario
Insights del coach
Resumen semanal

Lógica del sistema
SI día = imprevisto
→ excluir de KPIs

SI hábito falla repetidamente
→ sugerir ajuste

SI patrón detectado
→ generar insight

