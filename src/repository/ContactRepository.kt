package repository

import entities.ContactEntity

class ContactRepository {

    companion object { //É possível acessar esses métodos e atributos em várias instâncias

        private val contactList = mutableListOf<ContactEntity>()

        fun save(contact: ContactEntity) {

            contactList.add(contact)

        }


        fun delete(contact: ContactEntity) {

            var index = 0
            
            for (item in contactList.withIndex()) {
                if (item.value.name == contact.name && item.value.phone == contact.phone) {
                    index = item.index
                    break
                }
            }
            
            contactList.removeAt(index)
        }

        fun getList(): MutableList<ContactEntity> {

            return contactList
        }

    }

}