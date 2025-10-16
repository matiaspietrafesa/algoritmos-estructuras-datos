# 📊 Algoritmos y Estructuras de Datos

Un sistema de gestión de eventos implementado en Java que demuestra el uso de estructuras de datos fundamentales como listas, pilas y colas.

## 🎯 Descripción del Proyecto

Este proyecto implementa un sistema completo de autogestión de eventos que permite:

- **Gestión de Salas**: Registro y administración de salas con capacidad definida
- **Gestión de Eventos**: Creación, modificación y eliminación de eventos
- **Gestión de Clientes**: Registro de clientes con validación de cédula
- **Sistema de Entradas**: Compra, devolución y lista de espera
- **Sistema de Calificaciones**: Puntuación y comentarios de eventos

## 🏗️ Estructuras de Datos Implementadas

### 📋 Lista Enlazada (ListaNodos)
- Implementación de lista genérica con nodos
- Operaciones: agregar al inicio/final, agregar ordenado, eliminar, buscar
- Utilizada para almacenar salas, eventos, clientes y entradas

### 📚 Pila (Stack)
- Implementación de pila genérica LIFO
- Operaciones: push, pop, top, isEmpty
- Utilizada para el sistema de "deshacer últimas compras"

### 🚶‍♂️ Cola (Queue)
- Implementación de cola genérica FIFO
- Operaciones: encolar, desencolar, frente, isEmpty
- Utilizada para lista de espera de eventos

## 🔧 Tecnologías Utilizadas

- **Java 8+**
- **NetBeans IDE**
- **JUnit** para testing

## 📁 Estructura del Proyecto

```
src/
├── dominio/           # Clases del dominio
│   ├── Cliente.java
│   ├── Evento.java
│   ├── Sala.java
│   ├── Entrada.java
│   └── Calificacion.java
├── tads/              # Tipos Abstractos de Datos
│   ├── ListaNodos.java
│   ├── Pila.java
│   ├── Cola.java
│   └── Interfaces/
└── sistemaAutogestion/ # Lógica del sistema
    ├── Sistema.java
    ├── IObligatorio.java
    └── Retorno.java
```

## ⚡ Funcionalidades Principales

### Gestión de Salas
- Registro de salas con validación de capacidad
- Eliminación de salas
- Listado de salas disponibles

### Gestión de Eventos  
- Registro de eventos con asignación automática de sala
- Validación de disponibilidad de fechas
- Eliminación de eventos sin entradas vendidas
- Listado ordenado alfabéticamente

### Sistema de Entradas
- Compra de entradas con validación de capacidad
- Sistema de lista de espera automático
- Devolución de entradas
- Función "deshacer últimas N compras"

### Sistema de Calificaciones
- Calificación de eventos (1-10)
- Comentarios en eventos
- Cálculo de eventos mejor puntuados

## 🎮 Casos de Uso Implementados

1. **Análisis de Sala Óptima**: Algoritmo que determina si una sala tiene distribución óptima de asientos
2. **Gestión de Lista de Espera**: Asignación automática cuando hay devoluciones
3. **Estadísticas**: Compras por día de un mes específico
4. **Ranking**: Eventos mejor puntuados con promedio de calificaciones

## 🧪 Testing

El proyecto incluye tests unitarios que validan:
- Operaciones básicas de estructuras de datos
- Casos límite y validaciones
- Flujos completos del sistema

## 📈 Complejidad Computacional

- **Búsquedas**: O(n) en listas enlazadas
- **Inserciones ordenadas**: O(n)
- **Operaciones de pila/cola**: O(1)
- **Algoritmos de validación**: O(n·m) donde n=filas, m=columnas

## 🎓 Conceptos Aplicados

- **Programación Orientada a Objetos**
- **Generics en Java**
- **Manejo de excepciones**
- **Algoritmos de ordenamiento**
- **Estructuras de datos lineales**
- **Análisis de complejidad algorítmica**

---

Este proyecto fue desarrollado como parte del curso de Algoritmos y Estructuras de Datos, demostrando la implementación práctica de estructuras fundamentales en un contexto real de gestión empresarial.