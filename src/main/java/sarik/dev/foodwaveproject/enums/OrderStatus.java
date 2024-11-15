package sarik.dev.foodwaveproject.enums;

public enum OrderStatus {
    CONFIRMED,          /// Buyurtma tizim/yetkazuvchi tomonidan tasdiqlangan
    PREPARING,          /// Buyurtma tayyorlanmoqda
    READY_FOR_PICKUP,   /// Buyurtma olib ketish/yetkazish uchun tayyor
    OUT_FOR_DELIVERY,   /// Buyurtma yetkazib berilmoqda
    CANCELLED,          /// Buyurtma bekor qilingan
    COMPLETED,          /// Buyurtma muvaffaqiyatli yakunlangan
    PLACED,             /// Buyurtma mijoz tomonidan qo'yilgan
    DELIVERED,          /// Buyurtma mijozga yetkazilgan
}

