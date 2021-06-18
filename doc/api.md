---

# API contract

## Patient

### Получить/обновить информацию по пользователю

GET /api/v1/patients/me?snils={snils} \
POST /api/v1/patients/update-info

```json
{
  "id": "1",
  "optimal-blood-pressure": {
    "lower": 60,
    "upper": 120
  },
  "sex": "male",
  "birth-date": "yyyy-MM-dd",
  "name": "Иван",
  "last-name": "Иванов",
  "middle-name": "Иванович",
  "height": 183,
  "weight": 89
}
```

### Добавить измерение

POST /api/v1/diary/measurements/add

```json
{
  "patient-id": "1",
  "measure": {
    "timestamp": "yyyy-MM-dd'T'HH:mm ZZZZ",
    "lower": 60,
    "upper": 120,
    "pulse": 55,
    "quiz": {
      "health": "BAD",
      "comment": "Хреново чувствую себя"
    }
  }
}
```

### Дневник

GET /api/v1/diary/{patient-id}?from={date-from}&to={date-to}

```json
{
  "measures": [
    {
      "id": "32",
      "timestamp": "yyyy-MM-dd'T'HH:mm ZZZZ",
      "lower": 60,
      "upper": 120,
      "pulse": 55,
      "quiz": {
        "health": "GOOD",
        "comment": "Отлично чувствую себя"
      }
    },
    {
      "id": "34",
      "timestamp": "yyyy-MM-dd'T'HH:mm ZZZZ",
      "lower": 60,
      "upper": 135,
      "pulse": 55
    }
  ]
}
```

