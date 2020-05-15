package com.clarifai.demo

import org.springframework.web.bind.annotation.RestController
import clarifai2.api.ClarifaiBuilder
import clarifai2.dto.input.ClarifaiInput
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@CrossOrigin
@RestController
class Controller {

    @PostMapping("/")
    fun test(@RequestParam("image") file: MultipartFile): List<String?> {
        val byteArray = file.bytes
        val client = ClarifaiBuilder("KEY").buildSync()
        val generalModel = client.defaultModels.generalModel()
        val request = generalModel.predict().withInputs(
                ClarifaiInput.forImage(byteArray)
        )
        val result = request.executeSync().get()

        return result[0].data().map { it.name() }
    }
}