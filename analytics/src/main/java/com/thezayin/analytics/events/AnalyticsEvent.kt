package com.thezayin.analytics.events

import android.os.Bundle
import com.thezayin.analytics.utils.AnalyticsConstant.DETAILS_COPY_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.DETAILS_DELETE_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.DETAILS_PLAY_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.DETAILS_SHARE_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.HOME_COPY_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.HOME_JOKE_SAVED
import com.thezayin.analytics.utils.AnalyticsConstant.HOME_NEXT_JOKE_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.HOME_PLAY_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.HOME_SHARE_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.JOKE_ALL_DELETED
import com.thezayin.analytics.utils.AnalyticsConstant.JOKE_SAVED_SCREEN
import com.thezayin.analytics.utils.AnalyticsConstant.OPEN_IAP_SCREEN
import com.thezayin.analytics.utils.AnalyticsConstant.SAVED_DETAILS_SCREEN
import com.thezayin.analytics.utils.AnalyticsConstant.SETTING_ABOUT_US_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.SETTING_CONTACT_US_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.SETTING_MORE_APPS_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.SETTING_PRIVACY_POLICY_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.SETTING_REVIEW_SELECTED
import com.thezayin.analytics.utils.AnalyticsConstant.SETTING_SCREEN
import com.thezayin.analytics.utils.AnalyticsConstant.SETTING_TERMS_SELECTED

sealed class
AnalyticsEvent(
    val event: String? = null,
    val args: Bundle?
) {

    class JokeSavedScreen(
        status: String
    ) : AnalyticsEvent(
        JOKE_SAVED_SCREEN,
        Bundle().apply {
            putString("JokedSavedScreenSelected", status)
        }
    )

    class SettingScreen(
        status: String
    ) : AnalyticsEvent(
        SETTING_SCREEN,
        Bundle().apply {
            putString("SettingScreenSelected", status)
        }
    )

    class SavedDetailsScreen(
        status: String
    ) : AnalyticsEvent(
        SAVED_DETAILS_SCREEN,
        Bundle().apply {
            putString("SavedJokesDetailsScreenSelected", status)
        }
    )

    class OpenIapScreen(
        status: String
    ) : AnalyticsEvent(
        OPEN_IAP_SCREEN,
        Bundle().apply {
            putString("IAPScreenSelected", status)
        }
    )

    class HomeNextScreenSelected(
        status: String
    ) : AnalyticsEvent(
        HOME_NEXT_JOKE_SELECTED,
        Bundle().apply {
            putString("HomeSelectNextJokeSelected", status)
        }
    )

    class HomeJokeSavedSelected(
        status: String
    ) : AnalyticsEvent(
        HOME_JOKE_SAVED,
        Bundle().apply {
            putString("HomeJokeSavedSelected", status)
        }
    )

    class HomeCopyJokeSelected(
        status: String
    ) : AnalyticsEvent(
        HOME_COPY_SELECTED,
        Bundle().apply {
            putString("HomeCopyJokeSelected", status)
        }
    )

    class HomeShareJokeSelected(
        status: String
    ) : AnalyticsEvent(
        HOME_SHARE_SELECTED,
        Bundle().apply {
            putString("HomeShareJokeSelected", status)
        }
    )

    class HomePlayJokeSelected(
        status: String
    ) : AnalyticsEvent(
        HOME_PLAY_SELECTED,
        Bundle().apply {
            putString("HomePlayJokeSelected", status)
        }
    )

    class DeleteAllJokesSelected(
        status: String
    ) : AnalyticsEvent(
        JOKE_ALL_DELETED,
        Bundle().apply {
            putString("DeleteAllJokesSelected", status)
        }
    )

    class SavedCopyJokeSelected(
        status: String
    ) : AnalyticsEvent(
        DETAILS_COPY_SELECTED,
        Bundle().apply {
            putString("SavedCopyJokeSelected", status)
        }
    )

    class SavedShareJokeSelected(
        status: String
    ) : AnalyticsEvent(
        DETAILS_SHARE_SELECTED,
        Bundle().apply {
            putString("SavedShareJokeSelected", status)
        }
    )

    class SavedPlayJokeSelected(
        status: String
    ) : AnalyticsEvent(
        DETAILS_PLAY_SELECTED,
        Bundle().apply {
            putString("SavedPlayJokeSelected", status)
        }
    )

    class SavedDeleteJokeSelected(
        status: String
    ) : AnalyticsEvent(
        DETAILS_DELETE_SELECTED,
        Bundle().apply {
            putString("SavedDeleteJokeSelected", status)
        }
    )

    class ReviewSelected(
        status: String
    ) : AnalyticsEvent(
        SETTING_REVIEW_SELECTED,
        Bundle().apply {
            putString("ReviewSelected", status)
        }
    )

    class MoreAppsSelected(
        status: String
    ) : AnalyticsEvent(
        SETTING_MORE_APPS_SELECTED,
        Bundle().apply {
            putString("MoreAppsSelected", status)
        }
    )

    class AboutUsSelected(
        status: String
    ) : AnalyticsEvent(
        SETTING_ABOUT_US_SELECTED,
        Bundle().apply {
            putString("AboutUsSelected", status)
        }
    )

    class PrivacyPolicySelected(
        status: String
    ) : AnalyticsEvent(
        SETTING_PRIVACY_POLICY_SELECTED,
        Bundle().apply {
            putString("PrivacyPolicySelected", status)
        }
    )

    class TermsSelected(
        status: String
    ) : AnalyticsEvent(
        SETTING_TERMS_SELECTED,
        Bundle().apply {
            putString("TermsSelected", status)
        }
    )

    class ContactUsSelected(
        status: String
    ) : AnalyticsEvent(
        SETTING_CONTACT_US_SELECTED,
        Bundle().apply {
            putString("ContactUsSelected", status)
        }
    )
}


