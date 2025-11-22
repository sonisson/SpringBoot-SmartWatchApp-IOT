# SpringBoot-SmartWatchApp-IOT
- Springboot: `http://localhost:8080`
- MySQL: `db:3306`

## Bước 1. Chuẩn bị
- Cài Docker desktop.
- Tắt các dịch vụ sử dụng cổng 8080 và 3306.

## Bước 2. Clone repository
```bash
git clone https://github.com/sonisson/SpringBoot-SmartWatchApp-IOT
cd SpringBoot-SmartWatchApp-IOT
```

## Bước 3. Chạy docker
```bash
docker-compose up --build
```

## Bước 4. Kiểm tra kết nối
- Springboot: http://localhost:8080/api/test
- MySQL: username: root, password: 12356

## API
### Base URL
```bash
http://localhost:8080/api
```

### Token
```bash
Authorization: Bearer token_here
```
### GET /test-connection
Kiểm tra kết nối
#### Response:
```bash
Kết nối thành công.
```

### GET /test-login
Kiểm tra xác thực
#### Response:
```bash
Đăng nhập thành công.
```

### GET /test-user
Kiểm tra phân quyền
#### Response:
```bash
Đăng nhập thành công với quyền user.
```

### POST /auth/register
Đăng ký
#### Request:
```bash
{
    "username": "user1",
    "password": "user1",
    "name": "User 1"
}
```
#### Response:
```bash
{
    "id": 1,
    "username": "user1",
    "name": "User 1",
    "role": "USER",
    "createAt": "2025-11-22T01:18:33.007833",
    "updateAt": "2025-11-22T01:18:33.007833",
    "active": true
}
```

### POST /auth/login
Đăng nhập
#### Request:
```bash
{
    "username": "user1",
    "password": "user1",
}
```
#### Response:
```bash
{
    "token": "abcdefghijklmnopqrstuvwxyz",
    "user": {
        "id": 1,
        "username": "user1",
        "name": "User 1",
        "role": "USER",
        "createAt": "2025-11-22T01:18:33",
        "updateAt": "2025-11-22T01:18:33",
        "active": true
    }
}
```

### GET /search-user?username=user
Tìm kiếm user

#### Response:
```bash
[
    {
        "id": 1,
        "username": "user1",
        "name": "User 1",
        "role": "USER",
        "createAt": "2025-11-22T01:18:33",
        "updateAt": "2025-11-22T01:18:33",
        "active": true
    },
    {
        "id": 2,
        "username": "user2",
        "name": "User 2",
        "role": "USER",
        "createAt": "2025-11-22T01:44:38",
        "updateAt": "2025-11-22T01:44:38",
        "active": true
    }
]
```

### POST /register-device
Đăng ký thiết bị
#### Request:
```bash
{
    "id": "device-001",
    "name": "Device 001",
    "macAddress": "11:22:33:44:55:66:77:88"
}
```
#### Response:
```bash
{
    "id": "device-001",
    "name": "Device 001",
    "macAddress": "11:22:33:44:55:66:77:88",
    "ownerId": 1,
    "battery": 0,
    "lastSync": null,
    "createdAt": "2025-11-22T01:26:14.940132",
    "updatedAt": "2025-11-22T01:26:14.942665",
    "online": false
}
```

### GET /get-devices
Lấy danh sách thiết bị
#### Response:
```bash
[
    {
        "id": "device-001",
        "name": "Device 001",
        "macAddress": "11:22:33:44:55:66:77:88",
        "ownerId": 1,
        "battery": 0,
        "lastSync": null,
        "createdAt": "2025-11-22T01:26:15",
        "updatedAt": "2025-11-22T01:26:15",
        "online": false
    }
]
```

### POST /save-record
Lưu thông tin sức khỏe
> Request từ user1
#### Request:
```bash
{
    "deviceId": "device-001",
    "spo2": 88,
    "hearRate": 125,
    "signalQuality": "fair",
    "battery": 45,
    "recordedAt": "2025-11-21T22:50:00"
}
```
#### Response:
```bash
{
    "id": 1,
    "userId": 1,
    "deviceId": "device-001",
    "spo2": 88,
    "heartRate": 0,
    "signalQuality": "fair",
    "battery": 45,
    "recordedAt": "2025-11-21T22:50:00",
    "syncedAt": null
}
```

### GET /get-records?username=user1
Lấy thông tin sức khỏe
> Note: lấy 10 bản ghi mới nhất
#### Response:
```bash
[
    {
        "id": 1,
        "userId": 1,
        "deviceId": "device-001",
        "spo2": 88,
        "heartRate": 0,
        "signalQuality": "fair",
        "battery": 45,
        "recordedAt": "2025-11-21T22:50:00",
        "syncedAt": null
    }
]
```

### POST /save-record
Lưu thông tin ngã
#### Request:
```bash
{
    "deviceId": "device-001",
    "severity": "severe",
    "spo2": 88,
    "heartRate": 120,
    "longitude": 105.8412,
    "latitude": 21.2045,
    "status": "status",
    "detectedAt": "2025-11-21T22:50:00"
}
```
#### Response:
```bash
{
    "id": 1,
    "userId": 1,
    "deviceId": "device-001",
    "severity": "severe",
    "spo2": 88,
    "heartRate": 120,
    "longitude": 105.8412,
    "latitude": 21.2045,
    "status": "status",
    "detectedAt": "2025-11-21T22:50:00"
}
```

### GET /get-fall-events?username=user1
Lấy thông tin ngã
> Note: lấy 10 bản ghi mới nhất
#### Response:
```bash
[
    {
        "id": 1,
        "userId": 1,
        "deviceId": "device-001",
        "severity": "severe",
        "spo2": 88,
        "heartRate": 120,
        "longitude": 105.8412,
        "latitude": 21.2045,
        "status": "status",
        "detectedAt": "2025-11-21T22:50:00"
    }
]
```

### POST /invite
Yêu cầu theo dõi 
#### Request:
```bash
{
    "username": "user1"
}
```
#### Response:
```bash
{
    "id": 1,
    "patient": {
        "id": 1,
        "username": "user1",
        "name": "User 1",
        "role": "USER",
        "createAt": "2025-11-22T01:18:33",
        "updateAt": "2025-11-22T01:18:33",
        "active": true
    },
    "care": {
        "id": 2,
        "username": "user2",
        "name": "User 2",
        "role": "USER",
        "createAt": "2025-11-22T01:44:38",
        "updateAt": "2025-11-22T01:44:38",
        "active": true
    },
    "status": "pending",
    "createdAt": "2025-11-22T01:44:48.410711",
    "updatedAt": "2025-11-22T01:44:48.410711"
}
```

### POST /accept
Chấp nhận yêu cầu
#### Request:
```bash
{
    "userRelationId": 1
}
```
#### Response:
```bash
{
    "id": 1,
    "patient": {
        "id": 1,
        "username": "user1",
        "name": "User 1",
        "role": "USER",
        "createAt": "2025-11-22T01:18:33",
        "updateAt": "2025-11-22T01:18:33",
        "active": true
    },
    "care": {
        "id": 2,
        "username": "user2",
        "name": "User 2",
        "role": "USER",
        "createAt": "2025-11-22T01:44:38",
        "updateAt": "2025-11-22T01:44:38",
        "active": true
    },
    "status": "accepted",
    "createdAt": "2025-11-22T01:44:48",
    "updatedAt": "2025-11-22T01:47:38.587745"
}
```

### POST /reject
Từ chối yêu cầu
#### Request:
```bash
{
    "userRelationId": 1
}
```
#### Response:
```bash
{
    "id": 1,
    "patient": {
        "id": 1,
        "username": "user1",
        "name": "User 1",
        "role": "USER",
        "createAt": "2025-11-22T01:18:33",
        "updateAt": "2025-11-22T01:18:33",
        "active": true
    },
    "care": {
        "id": 2,
        "username": "user2",
        "name": "User 2",
        "role": "USER",
        "createAt": "2025-11-22T01:44:38",
        "updateAt": "2025-11-22T01:44:38",
        "active": true
    },
    "status": "rejected",
    "createdAt": "2025-11-22T01:44:48",
    "updatedAt": "2025-11-22T01:48:58.570762"
}
```

### GET /get-followers
Lấy danh sách followers
> Note: lấy cả bản ghi của cả 3 trạng thái (pending, accepted, rejected)
#### Response:
```bash
[
    {
        "id": 1,
        "patient": {
            "id": 1,
            "username": "user1",
            "name": "User 1",
            "role": "USER",
            "createAt": "2025-11-22T01:18:33",
            "updateAt": "2025-11-22T01:18:33",
            "active": true
        },
        "care": {
            "id": 2,
            "username": "user2",
            "name": "User 2",
            "role": "USER",
            "createAt": "2025-11-22T01:44:38",
            "updateAt": "2025-11-22T01:44:38",
            "active": true
        },
        "status": "accepted",
        "createdAt": "2025-11-22T01:44:48",
        "updatedAt": "2025-11-22T01:50:30"
    }
]
```

### GET /get-following
Lấy danh sách following
> Note: lấy cả bản ghi của cả 3 trạng thái (pending, accepted, rejected)

#### Response:
```bash
[
    {
        "id": 1,
        "patient": {
            "id": 1,
            "username": "user1",
            "name": "User 1",
            "role": "USER",
            "createAt": "2025-11-22T01:18:33",
            "updateAt": "2025-11-22T01:18:33",
            "active": true
        },
        "care": {
            "id": 2,
            "username": "user2",
            "name": "User 2",
            "role": "USER",
            "createAt": "2025-11-22T01:44:38",
            "updateAt": "2025-11-22T01:44:38",
            "active": true
        },
        "status": "accepted",
        "createdAt": "2025-11-22T01:44:48",
        "updatedAt": "2025-11-22T01:50:30"
    }
]
```

### Lỗi

#### Response:
```bash
{
    code: "ERROR_CODE",
    message: "error_message"
}
```

### Socket
Nhận thông tin sức khỏe hoặc sự kiện ngã

#### Connect
```bash
http://localhost:8080/ws
```
#### Subscribe
```bash
/topic/patient/{username}
```
#### Response
```bash
{
    "type": "RECORD",
    "data": {
        "id": 2,
        "userId": 1,
        "deviceId": "device-001",
        "spo2": 101,
        "heartRate": 0,
        "signalQuality": "fair",
        "battery": 45,
        "recordedAt": "2025-11-21T22:50:00",
        "syncedAt": null
    }
}
```
```bash
{
    "type": "FALL_EVENT",
    "data": {
        "id": 4,
        "userId": 1,
        "deviceId": "device-001",
        "severity": "severe",
        "spo2": 88,
        "heartRate": 132,
        "longitude": 105.8412,
        "latitude": 21.2045,
        "status": "status",
        "detectedAt": "2025-11-21T22:50:00"
    }
}
```

