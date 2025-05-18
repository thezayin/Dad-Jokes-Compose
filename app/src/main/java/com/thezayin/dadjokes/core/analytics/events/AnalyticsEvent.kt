package com.thezayin.dadjokes.core.analytics.events

import android.os.Bundle
import com.thezayin.dadjokes.core.analytics.utils.AnalyticsConstant

sealed class AnalyticsEvent(
    val event: String? = null,
    val args: Bundle?
) {
    /**
     * Represents the event when the user views a screen.
     *
     * @param screenName Name of the screen viewed.
     */
    class ScreenViewEvent(
        screenName: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SCREEN_VIEW,
        Bundle().apply {
            putString("screen_name", screenName)
        }
    )

    class AdRewardEvent(
        event: String,
        reward: String
    ) : AnalyticsEvent(
        event,
        Bundle().apply {
            putString("reward", reward)
        }
    )

    class SettingClickEvent(
        status: String
    ) : AnalyticsEvent(
        "SettingClicked",
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user clicks on the Privacy Policy link.
     */
    class SettingsPrivacyPolicy(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_PRIVACY_POLICY,
        Bundle().apply {
            putString(
                "status",
                status
            )  // status could indicate if the link was clicked or something else
        }
    )

    /**
     * Represents the event when a user clicks on the Terms & Conditions link.
     */
    class SettingsTermsConditions(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_TERMS_CONDITION,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user clicks on the 'Contact Us' link.
     */
    class SettingsContactUs(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_CONTACT_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user clicks on the "Rate Us" option in settings.
     */
    class SettingsRateUs(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_RATE_US,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when a user clicks on the "Leave a Rating & Review" option.
     */
    class LeaveRatingReview(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SETTINGS_MORE_APPS,
        Bundle().apply {
            putString("status", status)
        }
    )

    /**
     * Represents the event when an ad impression occurs.
     * This is useful for tracking ad performance and user engagement.
     *
     * @param adProvider Name of the ad provider (e.g., AdMob).
     * @param adType Type of the ad (e.g., Interstitial, Banner).
     */
    class AdImpressionEvent(
        adProvider: String,
        adType: String
    ) : AnalyticsEvent(
        AnalyticsConstant.AD_REVENUE,
        Bundle().apply {
            putString("ad_provider", adProvider)
            putString("ad_type", adType)
        }
    )

    /**
     * Represents the event when a user clicks on a banner ad.
     */
    class ShowBannerAdEvent(
        status: String
    ) : AnalyticsEvent(
        AnalyticsConstant.SCREEN_VIEW,
        Bundle().apply {
            putString("status", status)
        }
    )
}