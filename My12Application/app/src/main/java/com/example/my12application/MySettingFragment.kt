package com.example.my12application

import android.os.Bundle
import android.preference.PreferenceFragment
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


// PreferenceFragmentCompat을 상속받음
// 얘를 쓸 수 잇는 라이브러리를 추가하자
// build.gradle의 dependencies
class MySettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey) // xml 형태로 preference 설정
        // xml의 settings로 화면을 구성하겠다.

        // 사용자가 선택하는 것을 가지고 출력
        // edit preference의 것을 가져온다
        // 키 값을 이용해서 연결해준다
        val idPreference: EditTextPreference? = findPreference("id")
        // 사용자가 입력한 값을 요약해서 보여주겠다
        //idPreference?.summaryProvider = EditTextPreference.SimpleSummaryProvider.getInstance()
        idPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference> { preference ->
                // 사용자가 입력한 값을 담는 것
                val text: String? = preference.text
                // null 이면
                if (TextUtils.isEmpty(text)) {
                    "ID 설정이 되지 않았습니다."
                } else {
                    "설정된 ID는  $text 입니다."
                }
            }

        val bgColorPreference: ListPreference? = findPreference("bg_color")
        bgColorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()

        val txColorPreference: ListPreference? = findPreference("tx_color")
        txColorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
    }
}


