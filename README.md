D-Project из набора титульных и физических параметров (далее - начальные условия, НУ - initial conditions, IC. Смотри сущности IcOts+FormIcOts) формирует
набор титульных и физических параметров (далее - разовое решение, РР - one-time solution, Ots. Смотри сущности Ots+FormOts), по которым будет осуществляться 
процесс монтажа форм для печати тиража.

НУ передаются в программу json'ом вида:
{
    "titlePrinting": "test11",
    "shaftSize": 500,
    "formsIcOts": [
        { 
            "titleForm": "5",
            "quantityImprint": 2,
            "width": 500,
            "rightMargin": 44,
            "intervalLabels": 363
        },
        {
            "titleForm": "6",
            "quantityImprint": 2,
            "width": 500,
            "rightMargin": 44,
            "intervalLabels": 363
        }
    ]
}

Разовое решение возращается также json'ом. Он имеет вид:
{
    "titlePrinting": "test11",
    "shaftSize": 500,
    "formsOtsDto": [
        {
            "numberOnShaft": 2,
            "titleForm": "6",
            "indentationOnShaft": 44,
            "intervalLabels": 363,
            "toothOnShaft": 0
        },
        {
            "numberOnShaft": 1,
            "titleForm": "5",
            "indentationOnShaft": 44,
            "intervalLabels": 363,
            "toothOnShaft": 0
        }
    ],
    "author": {
        "username": "Alex"
    }
}
