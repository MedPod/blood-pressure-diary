package com.zhukofff.medpod

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zhukofff.medpod.databinding.FragmentMeasureArterialBinding
import java.time.LocalDateTime

class MeasureArterialFragment: Fragment() {

    private lateinit var binding: FragmentMeasureArterialBinding
    private lateinit var viewModel: MeasureArterialViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeasureArterialBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(MeasureArterialViewModel::class.java)
        // проверяем принимал ли пользователь лекарства
        var takenMedication = false
        binding.checkTakeMedication.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked == true) {
                takenMedication = true
            } else {
                takenMedication = false
            }
        }

        binding.buttonSave.setOnClickListener {

            var SystolicPressure: String = binding.editSystolic.text.toString()
            var DiastolicPressure: String = binding.editDiastolic.text.toString()

            // Защита от неккоректных введённых данных
            // Систолическое давление 90 - 135 мм ртутного столба
            // Диастоилческое давление 50 - 90 мм ртутного столба
            try {
                if (SystolicPressure.toInt() < 70 || SystolicPressure.toInt() > 250) {
                    Toast.makeText(
                        context,
                        "Систолическое давление введено некорректно!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (DiastolicPressure.toInt() < 35 || DiastolicPressure.toInt() > 150) {
                    Toast.makeText(
                        context,
                        "Диастолическое давление введено некорректно!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // дата измерения
                    var currentDate = LocalDateTime.now().toString()

                    // предупреждение -- давление выше 180/120 мм рт.
                    // измерения всё также желательно сохранить, но
                    // будет выведено предупреждащее сообщение

                    if (SystolicPressure.toInt() > 179 || DiastolicPressure.toInt() > 119) {
                        val AlertToast = Toast.makeText(
                            context,
                            "ВАШЕ ДАВЛЕНИЕ ОЧЕНЬ ВЫСОКОЕ! ОБРАТИТЕСЬ В ГОСПИТАЛЬ",
                            Toast.LENGTH_LONG
                        )
                        val view: View = AlertToast.view as View
                        view.setBackgroundColor(Color.parseColor("#FF0000"))
                        AlertToast.show()
                    }
                }


                viewModel.sendMeasurements(SystolicPressure,
                    DiastolicPressure)!!.observe(
                    viewLifecycleOwner,
                        Observer { measurement ->
                            val msg = measurement?.quiz?.comment
                            Toast.makeText(
                                context,
                                msg,
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                // обновляем элементы в фрагменте

                binding.editSystolic.text = null
                binding.checkTakeMedication.isChecked = false
                binding.editPulse.text = null
                binding.editDiastolic.text = null
        } catch (ex: Exception){
            Toast.makeText(
                context,
                "Введите значения пожалуйста",
                Toast.LENGTH_LONG
            ).show()
        }
        }
    }
}