/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.content.pm.parsing.component;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.annotation.StringRes;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;

import com.android.internal.util.DataClass;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link android.R.styleable#AndroidManifestAttribution &lt;attribution&gt;} tag parsed from the
 * manifest.
 *
 * @hide
 */
@DataClass(genAidl = false)
public class ParsedAttribution implements Parcelable {
    /** Maximum length of attribution tag */
    public static final int MAX_ATTRIBUTION_TAG_LEN = 50;

    /** Maximum amount of attributions per package */
    private static final int MAX_NUM_ATTRIBUTIONS = 1000;

    /** Tag of the attribution */
    public final @NonNull String tag;

    /** User visible label fo the attribution */
    public final @StringRes int label;

    /** Ids of previously declared attributions this attribution inherits from */
    public final @NonNull List<String> inheritFrom;

    /**
     * @return Is this set of attributions a valid combination for a single package?
     */
    public static boolean isCombinationValid(@Nullable List<ParsedAttribution> attributions) {
        if (attributions == null) {
            return true;
        }

        ArraySet<String> attributionTags = new ArraySet<>(attributions.size());
        ArraySet<String> inheritFromAttributionTags = new ArraySet<>();

        int numAttributions = attributions.size();
        if (numAttributions > MAX_NUM_ATTRIBUTIONS) {
            return false;
        }

        for (int attributionNum = 0; attributionNum < numAttributions; attributionNum++) {
            boolean wasAdded = attributionTags.add(attributions.get(attributionNum).tag);
            if (!wasAdded) {
                // feature id is not unique
                return false;
            }
        }

        for (int attributionNum = 0; attributionNum < numAttributions; attributionNum++) {
            ParsedAttribution feature = attributions.get(attributionNum);

            int numInheritFrom = feature.inheritFrom.size();
            for (int inheritFromNum = 0; inheritFromNum < numInheritFrom; inheritFromNum++) {
                String inheritFrom = feature.inheritFrom.get(inheritFromNum);

                if (attributionTags.contains(inheritFrom)) {
                    // Cannot inherit from a attribution that is still defined
                    return false;
                }

                boolean wasAdded = inheritFromAttributionTags.add(inheritFrom);
                if (!wasAdded) {
                    // inheritFrom is not unique
                    return false;
                }
            }
        }

        return true;
    }



    // Code below generated by codegen v1.0.23.
    //
    // DO NOT MODIFY!
    // CHECKSTYLE:OFF Generated code
    //
    // To regenerate run:
    // $ codegen $ANDROID_BUILD_TOP/frameworks/base/core/java/android/content/pm/parsing/component/ParsedAttribution.java
    //
    // To exclude the generated code from IntelliJ auto-formatting enable (one-time):
    //   Settings > Editor > Code Style > Formatter Control
    //@formatter:off


    @android.annotation.IntDef(prefix = "MAX_", value = {
        MAX_ATTRIBUTION_TAG_LEN,
        MAX_NUM_ATTRIBUTIONS
    })
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.SOURCE)
    @DataClass.Generated.Member
    public @interface Max {}

    @DataClass.Generated.Member
    public static String maxToString(@Max int value) {
        switch (value) {
            case MAX_ATTRIBUTION_TAG_LEN:
                    return "MAX_ATTRIBUTION_TAG_LEN";
            case MAX_NUM_ATTRIBUTIONS:
                    return "MAX_NUM_ATTRIBUTIONS";
            default: return Integer.toHexString(value);
        }
    }

    /**
     * Creates a new ParsedAttribution.
     *
     * @param tag
     *   Tag of the attribution
     * @param label
     *   User visible label fo the attribution
     * @param inheritFrom
     *   Ids of previously declared attributions this attribution inherits from
     */
    @DataClass.Generated.Member
    public ParsedAttribution(
            @NonNull String tag,
            @StringRes int label,
            @NonNull List<String> inheritFrom) {
        this.tag = tag;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, tag);
        this.label = label;
        com.android.internal.util.AnnotationValidations.validate(
                StringRes.class, null, label);
        this.inheritFrom = inheritFrom;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, inheritFrom);

        // onConstructed(); // You can define this method to get a callback
    }

    @Override
    @DataClass.Generated.Member
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        // You can override field parcelling by defining methods like:
        // void parcelFieldName(Parcel dest, int flags) { ... }

        dest.writeString(tag);
        dest.writeInt(label);
        dest.writeStringList(inheritFrom);
    }

    @Override
    @DataClass.Generated.Member
    public int describeContents() { return 0; }

    /** @hide */
    @SuppressWarnings({"unchecked", "RedundantCast"})
    @DataClass.Generated.Member
    protected ParsedAttribution(@NonNull Parcel in) {
        // You can override field unparcelling by defining methods like:
        // static FieldType unparcelFieldName(Parcel in) { ... }

        String _tag = in.readString();
        int _label = in.readInt();
        List<String> _inheritFrom = new ArrayList<>();
        in.readStringList(_inheritFrom);

        this.tag = _tag;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, tag);
        this.label = _label;
        com.android.internal.util.AnnotationValidations.validate(
                StringRes.class, null, label);
        this.inheritFrom = _inheritFrom;
        com.android.internal.util.AnnotationValidations.validate(
                NonNull.class, null, inheritFrom);

        // onConstructed(); // You can define this method to get a callback
    }

    @DataClass.Generated.Member
    public static final @NonNull Parcelable.Creator<ParsedAttribution> CREATOR
            = new Parcelable.Creator<ParsedAttribution>() {
        @Override
        public ParsedAttribution[] newArray(int size) {
            return new ParsedAttribution[size];
        }

        @Override
        public ParsedAttribution createFromParcel(@NonNull Parcel in) {
            return new ParsedAttribution(in);
        }
    };

    @DataClass.Generated(
            time = 1618351459610L,
            codegenVersion = "1.0.23",
            sourceFile = "frameworks/base/core/java/android/content/pm/parsing/component/ParsedAttribution.java",
            inputSignatures = "public static final  int MAX_ATTRIBUTION_TAG_LEN\nprivate static final  int MAX_NUM_ATTRIBUTIONS\npublic final @android.annotation.NonNull java.lang.String tag\npublic final @android.annotation.StringRes int label\npublic final @android.annotation.NonNull java.util.List<java.lang.String> inheritFrom\npublic static  boolean isCombinationValid(java.util.List<android.content.pm.parsing.component.ParsedAttribution>)\nclass ParsedAttribution extends java.lang.Object implements [android.os.Parcelable]\n@com.android.internal.util.DataClass(genAidl=false)")
    @Deprecated
    private void __metadata() {}


    //@formatter:on
    // End of generated code

}
