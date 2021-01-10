# AlphaBankTestTask
Даннный сервис позволяет узнать состояние курса доллара в сравнении с любой валютой и возвращает случайное gif изображение с сервиса ***https://giphy.com/***
Если курс валют на текующую дату вырос, то будет выдана гифка с тегом "rich", в противном случае с тегом "broke". Валюта задается с помощью http эндпоинта. Например:
***localhost/get/EUR*** -- данный запрос выведет состояние курса доллара в сравнении с евро, ***localhost/get/RUB*** с рублем и т.д.
Изменить API ключи внешних сервисов на свои можно внутрий файла config.json
# Руководство по запуску
Скачать Docker контейнер. Для этого можно использовать команду
***docker pull icedalek/alphabanktesttask***
Ссылка на репозиторий: 
***https://hub.docker.com/r/icedalek/alphabanktesttask***
Далее запустить контейнер с помощью команды: 
***docker run -p 8080:8080 -t  alphatesttask***
# Примеры использования сервиса:



![alt tag](https://i.ibb.co/YpNqtvJ/image.png")


# Поддреживаемые валюты: 

    AED,
    AFN,
    ALL,
    AMD,
    ANG,
    AOA,
    ARS,
    AUD,
    AWG,
    AZN,
    BAM,
    BBD,
    BDT,
    BGN,
    BHD,
    BIF,
    BMD,
    BND,
    BOB,
    BRL,
    BSD,
    BTC,
    BTN,
    BWP,
    BYN,
    BZD,
    CAD,
    CDF,
    CHF,
    CLF,
    CLP,
    CNH,
    CNY,
    COP,
    CRC,
    CUP,
    CVE,
    CZK,
    DJF,
    DKK,
    DOP,
    DZD,
    EGP,
    ERN,
    ETB,
    EUR,
    FJD,
    FKP,
    GBP,
    GEL,
    GGP,
    GHS,
    GIP,
    GMD,
    GNF,
    GTQ,
    GYD,
    HKD,
    HNL,
    HRK,
    HTG,
    HUF,
    IDR,
    ILS,
    IMP,
    INR,
    IQD,
    IRR,
    ISK,
    JEP,
    JMD,
    JOD,
    JPY,
    KES,
    KGS,
    KHR,
    KMF,
    KPW,
    KRW,
    KWD,
    KYD,
    KZT,
    LAK,
    LBP,
    LKR,
    LRD,
    LSL,
    LYD,
    MAD,
    MDL,
    MGA,
    MKD,
    MMK,
    MNT,
    MOP,
    MRO,
    MRU,
    MUR,
    MVR,
    MWK,
    MXN,
    MYR,
    MZN,
    NAD,
    NGN,
    NIO,
    NOK,
    NPR,
    NZD,
    OMR,
    PAB,
    PEN,
    PGK,
    PHP,
    PKR,
    PLN,
    PYG,
    QAR,
    RON,
    RSD,
    RUB,
    RWF,
    SAR,
    SBD,
    SCR,
    SDG,
    SEK,
    SGD,
    SHP,
    SLL,
    SOS,
    SRD,
    SSP,
    STD,
    STN,
    SVC,
    SYP,
    SZL,
    THB,
    TJS,
    TMT,
    TND,
    TOP,
    TRY,
    TTD,
    TWD,
    TZS,
    UAH,
    UGX,
    USD,
    UYU,
    UZS,
    VES,
    VND,
    VUV,
    WST,
    XAF,
    XAG,
    XAU,
    XCD,
    XDR,
    XOF,
    XPD,
    XPF,
    XPT,
    YER,
    ZAR,
    ZMW,
    ZWL