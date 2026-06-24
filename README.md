# BFHL API — Bajaj Finserv Health | Java Spring Boot

## ⚙️ Before You Run — Fill In Your Details

Open `src/main/resources/application.properties` and update:

```properties
user.full_name=Mridul Sharma          # Your full name (spaces OK)
user.dob=DDMMYYYY                     # e.g. 15082003
user.email=2310990907.cse@chitkara.edu.in
user.roll_number=2310990907           # Your actual roll number
```

These values drive `user_id`, `email`, and `roll_number` in every response.

---

## 🏗️ Build & Run Locally

```bash
# Build
mvn clean package -DskipTests

# Run
java -jar target/bfhl-1.0.0.jar
```

Server starts at `http://localhost:8080`

---

## 🧪 Test

```bash
mvn test
```

All 8 test cases run including the 3 examples from the question paper.

---

## 🌐 Deploy on Render (Free)

1. Push this project to a GitHub repo.
2. Go to [render.com](https://render.com) → New → Web Service.
3. Connect your repo.
4. Set:
   - **Build command**: `mvn clean package -DskipTests`
   - **Start command**: `java -jar target/bfhl-1.0.0.jar`
   - **Environment**: Java
5. Add environment variables in Render dashboard (optional — or keep in `application.properties`):
   - `USER_FULL_NAME`, `USER_DOB`, `USER_EMAIL`, `USER_ROLL_NUMBER`
6. Deploy. Your endpoint will be: `https://your-app.onrender.com/bfhl`

---

## 📬 API Usage

**POST** `/bfhl`

### Request
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

### Response (200 OK)
```json
{
  "is_success": true,
  "user_id": "mridul_sharma_15082003",
  "email": "2310990907.cse@chitkara.edu.in",
  "roll_number": "2310990907",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

---

## 📐 Logic Summary

| Field | Rule |
|---|---|
| `odd_numbers` | Numeric tokens where value % 2 ≠ 0 (as strings) |
| `even_numbers` | Numeric tokens where value % 2 = 0 (as strings) |
| `alphabets` | All-alpha tokens, uppercased |
| `special_characters` | Non-numeric, non-alpha tokens |
| `sum` | Sum of all numeric values, returned as string |
| `concat_string` | All alpha chars collected in order → reversed → alternating caps (index 0 = upper) |
| `user_id` | `full_name_ddmmyyyy` (lowercase, spaces → underscores) |

---

## 🗂️ Project Structure

```
src/
├── main/java/com/bajaj/bfhl/
│   ├── BfhlApplication.java          ← Entry point
│   ├── controller/BfhlController.java ← POST /bfhl
│   ├── service/
│   │   ├── BfhlService.java          ← Interface
│   │   └── BfhlServiceImpl.java      ← All logic
│   ├── dto/
│   │   ├── BfhlRequest.java          ← Request DTO
│   │   ├── BfhlResponse.java         ← Response DTO (Builder pattern)
│   │   └── ErrorResponse.java        ← Error DTO
│   └── exception/
│       └── GlobalExceptionHandler.java ← Graceful error handling
└── test/java/com/bajaj/bfhl/
    └── BfhlServiceImplTest.java      ← 8 unit tests
```
