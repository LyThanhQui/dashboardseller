# dashboardseller

## Backend (Spring Boot)

```bash
./mvnw spring-boot:run
```

Backend API: `http://localhost:8080/alert`

## Frontend (React + Vite)

```bash
cd frontend
npm install
npm run dev
```

Frontend URL: `http://localhost:5173`

The frontend calls `/alert` and uses Vite proxy to backend `http://localhost:8080`.
