# EV Service Center Maintenance Management System

Hệ thống quản lý bảo dưỡng/sửa chữa xe điện cho trung tâm dịch vụ (Service Center). Dự án gồm:

- **Backend (BE)**: Spring Boot (Java)
- **Frontend (FE)**: React + Vite

## 1. Chức năng chính (Features)

### 1.1. Xác thực & tài khoản
- **Đăng ký tài khoản khách hàng**
  - `POST /api/auth/register`
- **Đăng nhập** (trả về token)
  - `POST /api/auth/login`
- **Cập nhật thông tin người dùng** (họ tên, email, phone, mật khẩu…)
  - `PUT /api/update/{id}`

### 1.2. Quản lý lịch hẹn bảo dưỡng (Appointments)
#### Khách hàng (Customer)
- **Tạo lịch hẹn mới**
  - `POST /api/appointments`
- **Xem danh sách lịch hẹn của chính mình**
  - `GET /api/appointments`

#### Nhân viên/Quản lý/Kỹ thuật viên (Staff/Manager/Technician)
- **Duyệt/Chấp nhận lịch hẹn**
  - `PUT /api/appointments/{id}/accept`
- **Hủy lịch hẹn**
  - `PUT /api/appointments/{id}/cancel`
- **Chuyển trạng thái sang đang thực hiện (in progress)**
  - `PUT /api/appointments/{id}/inProgress`
- **Chuyển trạng thái sang chờ nhận xe (awaiting pickup / waiting)**
  - `PUT /api/appointments/{id}/waiting`
  - (Luồng này có thể tạo hóa đơn linh kiện nếu đủ điều kiện)
- **Tra cứu danh sách lịch hẹn theo trạng thái (theo Service Center)**
  - `GET /api/appointments/appointments/status/{status}`
- **Xem chi tiết 1 lịch hẹn theo ID (kèm thông tin liên quan)**
  - `GET /api/appointments/status/{id}`

#### Theo nhân sự được gán
- **Xem danh sách lịch hẹn theo staff/technician id**
  - `GET /api/appointments/staff/{id}`

#### Admin/Staff/Manager
- **Xem tất cả lịch hẹn (all fields)**
  - `GET /api/appointments/all`

#### Staff
- **Bàn giao xe/hoàn tất handover**
  - `PUT /api/appointments/{id}/handover`

### 1.3. Thanh toán (Payments)
- **Tạo URL thanh toán (VNPay/Payment Gateway)**
  - `POST /api/customer/payments/create`
- **Callback/Return từ cổng thanh toán**
  - `GET /api/auth/payments/return`
  - Backend sẽ redirect về FE: `/payment-return?status=...`
- **Xem lịch sử thanh toán của khách hàng**
  - `GET /api/customer/payments/history`
- **Hoàn tiền (Refund) (Staff/Manager)**
  - `POST /api/refunds`
  - `GET /api/auth/refund/return`
- **Thanh toán tiền mặt cho hóa đơn (Cash payment) (Staff/Manager)**
  - `PUT /api/cash-payment/{invoiceId}`
- **Thanh toán linh kiện theo appointment (Staff)**
  - `POST /api/part-payments/{appointmentId}`

### 1.4. Giao diện (Frontend routes)
Các route chính trên FE:
- `/` hoặc `/home`: Trang chủ
- `/login`: Đăng nhập
- `/booking`: Đặt lịch
- `/payment`: Trang thanh toán
- `/payment-return`: Trang hiển thị kết quả thanh toán
- `/profile`: Hồ sơ cá nhân
- `/mycar`: Quản lý xe của tôi
- `/staff`: Dashboard cho Staff
- `/technician`: Dashboard cho Technician
- `/admin`: Dashboard cho Admin
- `/manager/*`: Dashboard cho Manager (có sub-routes)

## 2. Công nghệ sử dụng
### Backend
- Java / Spring Boot
- Spring Security (JWT) 
- Maven
- Dockerfile (deploy)

### Frontend
- React + Vite
- Axios (custom `axiosClient`) gọi API

## 3. Chạy dự án (Quickstart)

### 3.1. Backend
```bash
cd Ev-System
./mvnw spring-boot:run
```

### 3.2. Frontend
```bash
npm install
npm run dev
```

## 4. Gợi ý cấu hình môi trường
- FE cần cấu hình `baseURL` trong `src/api/config.js` để trỏ tới BE.
- FE lưu `token` vào `localStorage` sau khi đăng nhập.


