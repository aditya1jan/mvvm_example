package com.nexxo.loginexample.home.model

data class HomeModel(
    val businessId: String,
    val businessName: String,
    val contractId: String,
    val description: String,
    val referenceId: String,
    val productId: Int,
    val productName: String,
    val pricingPlanId: Int,
    val pricingPlanName: String,
    val effectiveDate: String,
    val expiresOn: String,
    val status: String,
    val statusId: Int,
    val acquiringContractItems: List<AcquiringContractItem>,
    val paymentSettingItems: List<PaymentSettingItem>,
    val oneTimeChargeableItems: Any,
    val recurringChargeableItems: Any,
    val monthlyMinimumChargeableItems: Any,
    val signedAgreementFileUrl: String
) {
    data class AcquiringContractItem(
        val itemTypeId: Int,
        val itemName: String,
        val fixedPrice: String,
        val pricePercentage: String,
        val currencyCode: String,
        val unit: String,
        val discountAmount: Any,
        val discountPercentage: Any,
        val price: Any,
        val type: Any
    )

    data class PaymentSettingItem(
        val itemTypeId: Int,
        val itemName: String,
        val itemValue: String,
        val itemUnit: String
    )
}