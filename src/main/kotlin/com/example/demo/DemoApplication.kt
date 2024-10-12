package com.example.demo


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@RestController
class HelloController {
	@GetMapping("/ex1")
	fun hello(): String {
		return "Any fool can write code that a computer can understand. Good programmers write code that humans can understand"
	}
}


@Controller
class MultiplicationController {

	@GetMapping("/ex2")
	fun index(): ModelAndView {
		return ModelAndView("form")
	}

	@GetMapping("/multiply")
	fun multiply(@RequestParam number: Int): ModelAndView {
		val result = StringBuilder()
		for (i in 1..10) {
			result.append("$number x $i = ${number * i}<br/>")
		}
		val modelAndView = ModelAndView("result")
		modelAndView.addObject("result", result.toString())
		return modelAndView
	}
}


@Controller
class CalculationController {

	@GetMapping("/ex3")
	fun index(): ModelAndView {
		return ModelAndView("calc_form")
	}

	@GetMapping("/calculate")
	fun calculate(
		@RequestParam number1: Double,
		@RequestParam number2: Double,
		@RequestParam number3: Double,
		@RequestParam action: String
	): ModelAndView {
		val result = when (action) {
			"min" -> "Мінімальне значення: ${minOf(number1, number2, number3)}"
			"max" -> "Максимальне значення: ${maxOf(number1, number2, number3)}"
			"avg" -> "Середнє арифметичне: ${(number1 + number2 + number3) / 3}"
			else -> "Невірна дія"
		}

		val modelAndView = ModelAndView("calc_result")
		modelAndView.addObject("result", result)
		return modelAndView
	}
}