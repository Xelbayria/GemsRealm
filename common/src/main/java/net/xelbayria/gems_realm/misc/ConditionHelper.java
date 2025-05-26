package net.xelbayria.gems_realm.misc;

import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Objects;

public class ConditionHelper {

    /// A list of conditions to ensure some model files are skipped/excluded
    public static List<Boolean> createConditions(ResourceLocation oldRes, String ignoreIfContainIron, int index) {
        return List.of(
                oldRes.getPath().substring(index + 1).contains(ignoreIfContainIron),
                (
                        //EXCLUDE
                        !(
                                oldRes.getPath().contains("/parent/") // model files in parent folder must be excluded
                                || oldRes.toString().contains("mcwwindows:block/parent/curtain_rod") //TEMP - must be excluded
                        )
                                || oldRes.toString().contains("mcwbridges:block/support_pier/parent") //TEMP - must be included
                ),
                !oldRes.getPath().contains("template") // model files in template folder must be excluded
        );
    }

    //  if (ignoreIfFromStone != null && (idx != -1) && path.substring(idx + 1).contains(ignoreIfFromStone)
    //                    && !path.contains("/parent/") && !path.contains("template")) {

    public static Boolean excludeIf(ResourceLocation oldRes, String ignoreIfContainIron, int index) {
        if (Objects.isNull(ignoreIfContainIron) || index == -1) {
            return false;
        }
        return isAllTrue(createConditions(oldRes, ignoreIfContainIron, index));
    }

    public static boolean isAllTrue(List<Boolean> list) {
        return list.stream().allMatch(Boolean::booleanValue);
    }

}
