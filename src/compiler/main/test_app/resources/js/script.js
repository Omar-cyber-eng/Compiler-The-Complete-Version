// ============================================================================
// script.js — إعادة التوليد من جهة المتصفح (client-side regeneration)
//
// يجيب على سؤال المعيدة: "مَن يستمع للمتغيرات ويعيد التوليد؟"
// هذا الملف يستمع (polling) لتغيّرات البيانات عبر واجهة /api/products،
// وعند أي تغيير (إضافة/حذف منتج) يعيد توليد الصفحة تلقائياً.
// ============================================================================

let lastSignature = null;

// يجلب البيانات الحالية ويبني "توقيعاً" يمثّل حالتها
async function fetchSignature() {
    const res = await fetch("/api/products");
    const products = await res.json();
    // التوقيع = تمثيل نصي مبسّط للبيانات (id + name + price)
    return JSON.stringify(products.map(p => [p.id, p.name, p.price]));
}

// يستمع للتغيّرات: إن اختلفت البيانات عن آخر مرة → أعد توليد الصفحة
async function checkForChanges() {
    try {
        const signature = await fetchSignature();
        if (lastSignature === null) {
            lastSignature = signature;          // أول قراءة: خزّن الحالة فقط
        } else if (signature !== lastSignature) {
            lastSignature = signature;
            console.log("script.js: data changed → regenerating page");
            location.reload();                  // إعادة التوليد
        }
    } catch (e) {
        console.error("script.js: regeneration check failed", e);
    }
}

// "يستمع" كل ثانيتين
setInterval(checkForChanges, 2000);
checkForChanges();

console.log("script.js: listening for data changes (client-side regeneration active)");
