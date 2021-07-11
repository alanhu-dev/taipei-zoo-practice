# taipei-zoo-practice
* MVVM
* kotlin coroutines
* Android architecture components
* Dependency Injection: Hilt
* Room database
* okHttp
* Retrofit
* Gson
* Glide

## UI
* MainActivity 主頁，點擊 BottomNav 在 fragment 使用 transition (MaterialFadeThrough)。
* PavilionActivity 館區詳細資訊頁，手勢效果使用 Motion layout。
* 植物詳細資訊使用 BottomSheetDialogFragment。
* 所有圖檔轉為 webp。

## Others
* 使用 google 建議的 repository pattern，將 DataSource 分為 LocalDataSource、RemoteDataSource。每次請求到的資料也會存進資料庫，讓使用者在無網路可用時仍可瀏覽先前的紀錄。
* PavilionFragment 園內植物，使用了 android architecture componment 的 paging，達到此效果：每次往下滑動根據傳入 api 的 limit 只取得定量的 data，之後透過 offset 來調整下一次取得 data 的位置。
減少一次讀取大量 data 過久的情況。

## 😵
週六操作 api 時還很快速且正常，但星期日開始出現：javax.net.ssl.SSLHandshakeException: Chain validation failed。使用 chrome 等瀏覽器、Postman 等，同樣會提示證書過期等不安全資訊。因此改為使用 UnsafeOkHttpClient (at SingletonModule)。
