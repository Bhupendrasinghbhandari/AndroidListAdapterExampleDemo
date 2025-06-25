package com.example.androidlistadapterexampledemo.utils


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductListResponse(
    @SerializedName("bottom_text_data")
    val bottomTextData: BottomTextData? = BottomTextData(),
    @SerializedName("cart_lottie")
    val cartLottie: String? = "",
    @SerializedName("cart_offer")
    val cartOffer: List<Any?>? = listOf(),
    @SerializedName("category_data_list")
    val categoryDataList: List<CategoryData>? = listOf(),
    @SerializedName("extra_keys")
    val extraKeys: ExtraKeys? = ExtraKeys(),
    @SerializedName("info_msgs")
    val infoMsgs: String? = "",
    @SerializedName("membership_info")
    val membershipInfo: MembershipInfo? = MembershipInfo(),
    @SerializedName("show_cart_animation")
    val showCartAnimation: Boolean? = false,
    @SerializedName("show_cart_on_plp_search")
    val showCartOnPlpSearch: Boolean? = false,
    @SerializedName("show_milk_kit")
    val showMilkKit: Boolean? = false,
    @SerializedName("to_redirect_to_plpfrom_calendar")
    val toRedirectToPlpfromCalendar: Boolean? = false,
    @SerializedName("to_show_product_category")
    val toShowProductCategory: Boolean? = false,
    @SerializedName("to_show_recomm_in_plp")
    val toShowRecommInPlp: Boolean? = false
) {
    @Keep
    data class BottomTextData(
        @SerializedName("cta_1")
        val cta1: String? = "",
        @SerializedName("cta_2")
        val cta2: String? = "",
        @SerializedName("lottie_url")
        val lottieUrl: String? = "",
        @SerializedName("sub_title")
        val subTitle: String? = "",
        @SerializedName("title")
        val title: String? = ""
    )

    @Keep
    data class CategoryData(
        @SerializedName("background_color")
        val backgroundColor: Any? = Any(),
        @SerializedName("category_bg_color")
        val categoryBgColor: Any? = Any(),
        @SerializedName("category_id")
        val categoryId: Int? = 0,
        @SerializedName("category_name")
        val categoryName: String? = "",
        @SerializedName("icon")
        val icon: String? = "",
        @SerializedName("icon_lottie")
        val iconLottie: String? = "",
        @SerializedName("product_info")
        val productInfo: Any? = Any(),
        @SerializedName("product_variant_info")
        val productVariantInfo: List<ProductVariantInfo>? = listOf(),
        @SerializedName("rounding_required")
        val roundingRequired: Boolean? = false
    ) {
        @Keep
        data class ProductVariantInfo(
            @SerializedName("id")
            val id: Int? = 0,
            @SerializedName("imageUrl")
            val imageUrl: String? = "",
            @SerializedName("meta_name")
            val metaName: String? = "",
            @SerializedName("name")
            val name: String? = "",
            @SerializedName("product_info")
            val productInfo: List<ProductInfo?>? = listOf()
        ) {
            @Keep
            data class ProductInfo(
                @SerializedName("add_allowed")
                val addAllowed: Boolean? = false,
                @SerializedName("cart_quantity")
                val cartQuantity: Int? = 0,
                @SerializedName("cart_sequence")
                val cartSequence: Int? = 0,
                @SerializedName("category_id")
                val categoryId: Int? = 0,
                @SerializedName("category_name")
                val categoryName: String? = "",
                @SerializedName("default")
                val default: Boolean? = false,
                @SerializedName("delivery_required")
                val deliveryRequired: Boolean? = false,
                @SerializedName("display_name")
                val displayName: String? = "",
                @SerializedName("display_unit")
                val displayUnit: String? = "",
                @SerializedName("dynamic_savings")
                val dynamicSavings: Boolean? = false,
                @SerializedName("frequencies")
                val frequencies: List<Frequency?>? = listOf(),
                @SerializedName("id")
                val id: Int? = 0,
                @SerializedName("image")
                val image: String? = "",
                @SerializedName("in_stock")
                val inStock: Boolean? = false,
                @SerializedName("is_price_change_toast_shown")
                val isPriceChangeToastShown: Boolean? = false,
                @SerializedName("max_order_unit")
                val maxOrderUnit: Int? = 0,
                @SerializedName("meta_name")
                val metaName: String? = "",
                @SerializedName("multiplier_unit")
                val multiplierUnit: Double? = 0.0,
                @SerializedName("multiply_unit")
                val multiplyUnit: String? = "",
                @SerializedName("name")
                val name: String? = "",
                @SerializedName("offer_label_info")
                val offerLabelInfo: OfferLabelInfo? = OfferLabelInfo(),
                @SerializedName("order_frequency")
                val orderFrequency: OrderFrequency? = OrderFrequency(),
                @SerializedName("order_id")
                val orderId: Int? = 0,
                @SerializedName("order_type")
                val orderType: Int? = 0,
                @SerializedName("packaging_required")
                val packagingRequired: Boolean? = false,
                @SerializedName("prefill_quantity")
                val prefillQuantity: Int? = 0,
                @SerializedName("previous_order_quantity")
                val previousOrderQuantity: Int? = 0,
                @SerializedName("price_info")
                val priceInfo: PriceInfo? = PriceInfo(),
                @SerializedName("product_discount_percentage")
                val productDiscountPercentage: String? = "",
                @SerializedName("product_label_info")
                val productLabelInfo: ProductLabelInfo? = ProductLabelInfo(),
                @SerializedName("rotate_rtb")
                val rotateRtb: Boolean? = false,
                @SerializedName("rounding_required")
                val roundingRequired: Boolean? = false,
                @SerializedName("rtb")
                val rtb: List<String?>? = listOf(),
                @SerializedName("segment_id")
                val segmentId: Int? = 0,
                @SerializedName("show_icon_info")
                val showIconInfo: Boolean? = false,
                @SerializedName("sub_products")
                val subProducts: List<SubProduct?>? = listOf(),
                @SerializedName("subscribable")
                val subscribable: Boolean? = false,
                @SerializedName("tags")
                val tags: List<Int?>? = listOf(),
                @SerializedName("threshold_breached")
                val thresholdBreached: Boolean? = false,
                @SerializedName("to_multiply_by_quantity")
                val toMultiplyByQuantity: Boolean? = false,
                @SerializedName("unit_size")
                val unitSize: String? = "",
                @SerializedName("unit_type")
                val unitType: String? = ""
            ) {
                @Keep
                data class Frequency(
                    @SerializedName("id")
                    val id: Int? = 0,
                    @SerializedName("name")
                    val name: String? = ""
                )

                @Keep
                data class OfferLabelInfo(
                    @SerializedName("label_background_color")
                    val labelBackgroundColor: String? = "",
                    @SerializedName("label_icon")
                    val labelIcon: String? = "",
                    @SerializedName("label_text")
                    val labelText: String? = "",
                    @SerializedName("label_text_color")
                    val labelTextColor: String? = ""
                )

                @Keep
                data class OrderFrequency(
                    @SerializedName("id")
                    val id: Int? = 0,
                    @SerializedName("name")
                    val name: String? = ""
                )

                @Keep
                data class PriceInfo(
                    @SerializedName("already_availed_order_count")
                    val alreadyAvailedOrderCount: Int? = 0,
                    @SerializedName("discount")
                    val discount: Int? = 0,
                    @SerializedName("is_customer_member")
                    val isCustomerMember: Boolean? = false,
                    @SerializedName("is_offer_deduction_from_membership")
                    val isOfferDeductionFromMembership: Boolean? = false,
                    @SerializedName("is_offer_valid_for_subscription")
                    val isOfferValidForSubscription: Boolean? = false,
                    @SerializedName("member_price")
                    val memberPrice: Double? = 0.0,
                    @SerializedName("membership_left_benefit")
                    val membershipLeftBenefit: Double? = 0.0,
                    @SerializedName("membership_product_price_title")
                    val membershipProductPriceTitle: String? = "",
                    @SerializedName("mrp")
                    val mrp: Double? = 0.0,
                    @SerializedName("new_customer_subscription_offer")
                    val newCustomerSubscriptionOffer: Boolean? = false,
                    @SerializedName("offer_id")
                    val offerId: Int? = 0,
                    @SerializedName("offer_price")
                    val offerPrice: Double? = 0.0,
                    @SerializedName("offer_type")
                    val offerType: String? = "",
                    @SerializedName("offer_valid_from")
                    val offerValidFrom: String? = "",
                    @SerializedName("offer_valid_till")
                    val offerValidTill: String? = "",
                    @SerializedName("po_id")
                    val poId: Int? = 0,
                    @SerializedName("product_offer_name")
                    val productOfferName: String? = "",
                    @SerializedName("remaining_offer_order_quantity")
                    val remainingOfferOrderQuantity: String? = "",
                    @SerializedName("remaining_offer_quantity")
                    val remainingOfferQuantity: Int? = 0,
                    @SerializedName("savings_text")
                    val savingsText: String? = "",
                    @SerializedName("selling_price")
                    val sellingPrice: Double? = 0.0,
                    @SerializedName("subscribe_offer_text")
                    val subscribeOfferText: String? = ""
                )

                @Keep
                data class ProductLabelInfo(
                    @SerializedName("label_icon")
                    val labelIcon: String? = "",
                    @SerializedName("label_text")
                    val labelText: String? = "",
                    @SerializedName("label_text_color")
                    val labelTextColor: String? = "",
                    @SerializedName("quality_report_text")
                    val qualityReportText: String? = "",
                    @SerializedName("quality_report_url")
                    val qualityReportUrl: String? = ""
                )

                @Keep
                data class SubProduct(
                    @SerializedName("display_name")
                    val displayName: String? = "",
                    @SerializedName("display_unit")
                    val displayUnit: String? = "",
                    @SerializedName("id")
                    val id: Int? = 0,
                    @SerializedName("image")
                    val image: String? = "",
                    @SerializedName("name")
                    val name: String? = "",
                    @SerializedName("quantity")
                    val quantity: Int? = 0
                )
            }
        }
    }

    @Keep
    data class ExtraKeys(
        @SerializedName("city_id")
        val cityId: Int? = 0,
        @SerializedName("customer_cart_offer_id")
        val customerCartOfferId: Int? = 0,
        @SerializedName("default_amount_new_customer")
        val defaultAmountNewCustomer: Int? = 0,
        @SerializedName("default_amount_old_customer")
        val defaultAmountOldCustomer: Int? = 0,
        @SerializedName("discard_free_product_if_in_cart")
        val discardFreeProductIfInCart: Boolean? = false,
        @SerializedName("discount")
        val discount: Int? = 0,
        @SerializedName("enhanced_search")
        val enhancedSearch: Boolean? = false,
        @SerializedName("enlarge_ctafor_membership")
        val enlargeCtaforMembership: Boolean? = false,
        @SerializedName("extra_charges")
        val extraCharges: ExtraCharges? = ExtraCharges(),
        @SerializedName("heterogeneous_confirm_redirect")
        val heterogeneousConfirmRedirect: Int? = 0,
        @SerializedName("info_msgs")
        val infoMsgs: String? = "",
        @SerializedName("is_customer_member")
        val isCustomerMember: Boolean? = false,
        @SerializedName("isTrialPlan")
        val isTrialPlan: Boolean? = false,
        @SerializedName("locality_id")
        val localityId: Int? = 0,
        @SerializedName("membership_configuration_id")
        val membershipConfigurationId: Int? = 0,
        @SerializedName("on_vacation")
        val onVacation: Boolean? = false,
        @SerializedName("search_on_review")
        val searchOnReview: Boolean? = false,
        @SerializedName("to_show_membership_bottom_drawer")
        val toShowMembershipBottomDrawer: Boolean? = false,
        @SerializedName("trial_plan")
        val trialPlan: Boolean? = false
    ) {
        @Keep
        data class ExtraCharges(
            @SerializedName("delivery_charges")
            val deliveryCharges: List<DeliveryCharge?>? = listOf(),
            @SerializedName("delivery_charges_text")
            val deliveryChargesText: String? = "",
            @SerializedName("packaging_charge")
            val packagingCharge: List<PackagingCharge?>? = listOf(),
            @SerializedName("packaging_charge_text")
            val packagingChargeText: String? = ""
        ) {
            @Keep
            data class DeliveryCharge(
                @SerializedName("charge")
                val charge: Int? = 0,
                @SerializedName("deduct_from_benefit")
                val deductFromBenefit: Boolean? = false,
                @SerializedName("delivery_charge_review_cart_text")
                val deliveryChargeReviewCartText: String? = "",
                @SerializedName("delivery_charge_text")
                val deliveryChargeText: String? = "",
                @SerializedName("include_member_for_charges")
                val includeMemberForCharges: Boolean? = false,
                @SerializedName("order_amount_from")
                val orderAmountFrom: Double? = 0.0,
                @SerializedName("order_amount_to")
                val orderAmountTo: Int? = 0,
                @SerializedName("packaging_charge_review_cart_text")
                val packagingChargeReviewCartText: String? = "",
                @SerializedName("packaging_charge_text")
                val packagingChargeText: String? = ""
            )

            @Keep
            data class PackagingCharge(
                @SerializedName("charge")
                val charge: Int? = 0,
                @SerializedName("deduct_from_benefit")
                val deductFromBenefit: Boolean? = false,
                @SerializedName("delivery_charge_review_cart_text")
                val deliveryChargeReviewCartText: String? = "",
                @SerializedName("delivery_charge_text")
                val deliveryChargeText: String? = "",
                @SerializedName("include_member_for_charges")
                val includeMemberForCharges: Boolean? = false,
                @SerializedName("order_amount_from")
                val orderAmountFrom: Int? = 0,
                @SerializedName("order_amount_to")
                val orderAmountTo: Int? = 0,
                @SerializedName("packaging_charge_review_cart_text")
                val packagingChargeReviewCartText: String? = "",
                @SerializedName("packaging_charge_text")
                val packagingChargeText: String? = ""
            )
        }
    }

    @Keep
    data class MembershipInfo(
        @SerializedName("member_saving_text")
        val memberSavingText: String? = ""
    )
}