package com.sbe.rand.inve.sttwo.domain

import com.sbe.rand.inve.sttwo.util.Constants.bankCredit
import com.sbe.rand.inve.sttwo.util.Constants.budgetPersonalFin
import com.sbe.rand.inve.sttwo.util.Constants.finFraud
import com.sbe.rand.inve.sttwo.util.Constants.invest
import com.sbe.rand.inve.sttwo.util.Constants.moneyHistory
import com.sbe.rand.inve.sttwo.util.Constants.taxes

enum class Menu(
    val text: String, val description: String, val listQuestions: List<QuizQuestion>
) {
    MONEY_HISTORY(
        "История денег и экономика",
        "Факты и мифы о происхождении и развитии рубля как валюты.",
        moneyHistory
    ),
    BUDGET_PERSONAL_FIN(
        "Бюджетирование и личные финансы",
        "Проверим, хорошо ли вы разбираешься в экономии и повседневных расходах.",
        budgetPersonalFin
    ),
    BANK_CREDIT(
        "Банковские продукты и кредиты",
        "Узнайте, насколько вы осведомлены о банковских услугах, картах и кредитах.",
        bankCredit
    ),
    INVEST(
        "Инвестиции и фондовый рынок",
        "Правда ли вы знаете, как работают инвестиции, акции и доходность?",
        invest
    ),
    TAXES(
        "Налоги и отчисления",
        "Разберёмся, что реально требует государство, а что — домыслы.",
        taxes
    ),
    FIN_FRAUD(
        "Финансовое мошенничество",
        "Сможете отличить реальные угрозы от надуманных страшилок?",
        finFraud
    )
}