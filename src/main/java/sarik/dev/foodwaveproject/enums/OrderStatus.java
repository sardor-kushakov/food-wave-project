package sarik.dev.foodwaveproject.enums;

public enum OrderStatus {
    PLACED,             // Buyurtma mijoz tomonidan qo'yilgan
    CONFIRMED,          // Buyurtma tizim/yetkazuvchi tomonidan tasdiqlangan
    PREPARING,          // Buyurtma tayyorlanmoqda
    READY_FOR_PICKUP,   // Buyurtma olib ketish/yetkazish uchun tayyor
    OUT_FOR_DELIVERY,   // Buyurtma yetkazib berilmoqda
    DELIVERED,          // Buyurtma mijozga yetkazilgan
    COMPLETED,          // Buyurtma muvaffaqiyatli yakunlangan
    CANCELLED           // Buyurtma bekor qilingan
}
