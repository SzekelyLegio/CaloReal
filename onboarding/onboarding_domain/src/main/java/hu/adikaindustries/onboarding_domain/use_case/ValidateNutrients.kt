package hu.adikaindustries.onboarding_domain.use_case

import hu.adikaindustries.core.util.UiText
import hu.adikaindustries.core.R

class ValidateNutrients {
    operator fun invoke(
        carbRatioText:String,
        fatRatioText:String,
        proteinRatioText:String
    ):Result{
            val carbsRatio = carbRatioText.toIntOrNull()
            val proteinRatio=proteinRatioText.toIntOrNull()
            val fatRatio = fatRatioText.toIntOrNull()
            if(carbsRatio == null || proteinRatio  == null || fatRatio == null){
                return Result.Error(UiText.StringResource(R.string.error_invalid_values))
            }
            if(carbsRatio + proteinRatio + fatRatio != 100){
                return Result.Error(UiText.StringResource(R.string.error_not_100_percent))
            }
            return Result.Success(
                carbsRatio / 100f,
                proteinRatio/ 100f,
                   fatRatio/100f)
    }

    sealed class Result{
        data class Success(
            val carbsRatio:Float,
            val proteinRatio:Float,
            val fatRatio:Float
        ):Result()
        data class Error(val message:UiText):Result()
    }
}