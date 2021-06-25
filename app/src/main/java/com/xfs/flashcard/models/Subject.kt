package com.xfs.flashcard.models

class Subject {
        var id : Int
        var name: String
        var image: String

        constructor(id: Int, name : String, image : String) {
                this.id=id
                this.name=name
                this.image = image
        }
}