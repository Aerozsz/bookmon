package my.kl.nightowl.core

/**
 * The groups places are shown under. [colorArgb] is used for map markers and badges.
 */
enum class Category(
    val label: String,
    val shortLabel: String,
    val emoji: String,
    val colorArgb: Long,
) {
    KONBINI("Konbini & convenience stores", "Konbini", "🏪", 0xFF4FC3F7),
    RESTAURANT("Mamak & restaurants", "Restaurants", "🍛", 0xFFFFB74D),
    FAST_FOOD("Fast food", "Fast food", "🍔", 0xFFFF7A7A),
    CAFE("Cafés & desserts", "Cafés", "☕", 0xFFC8A27A),
    NIGHTLIFE("Bars & nightlife", "Bars", "🍸", 0xFFC792EA),
    PETROL("Petrol station shops", "Petrol", "⛽", 0xFF7BD88F),
    PHARMACY("Pharmacies", "Pharmacy", "💊", 0xFF4DD0C4),
    GROCERY("Supermarkets & groceries", "Grocery", "🛒", 0xFFFFE066),
    OTHER("Other shops & services", "Other", "🛍️", 0xFFA0AEC8),
}
