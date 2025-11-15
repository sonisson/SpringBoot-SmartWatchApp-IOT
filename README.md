# SpringBoot-SmartWatchApp-IOT
- Springboot: `http://localhost:8080`
- MySQL: `db:3306`

### Bước 1. Chuẩn bị
- Cài Docker desktop.
- Tắt các dịch vụ sử dụng cổng 8080 và 3306.

### Bước 2. Clone repository
```bash
git clone https://github.com/sonisson/SpringBoot-SmartWatchApp-IOT
cd SpringBoot-SmartWatchApp-IOT
```

### Bước 3. Chạy docker
```bash
docker-compose up --build
```

### Bước 4. Kiểm tra kết nối
- Springboot: http://localhost:8080/api/test
- MySQL: username: root, password: 12356