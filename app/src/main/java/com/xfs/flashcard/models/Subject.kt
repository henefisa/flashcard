package com.xfs.flashcard.models

class Subject {
        var id : String
        var name: String
        var image: String

        constructor(id: String, name : String, image : String) {
                this.id=id
                this.name=name
                this.image = image
        }
}