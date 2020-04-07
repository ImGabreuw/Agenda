package business

import entities.ContactEntity
import repository.ContactRepository
import java.lang.Exception

class ContactBusiness {

    private fun validateSave(name: String, phone: String) {

        if (name == "") {
            throw Exception("Nome é obrigatório!")
        }

        if (phone == "") {
            throw Exception("Nome é obrigatório!")
        }

    }

    private fun validateDelete(name: String, phone: String) {

        if (name == "" || phone == "") {
            throw Exception("É necessário selecionar um contato antes de remover!")
        }

    }

    fun getContactCountDescription(): String {

        val list = getList()

        return  when {

            list.isEmpty() -> "0 contato"

            list.size == 1 -> "1 contato"

            else -> "${list.size} contatos"

        }
    }

    fun save(name: String, phone: String) {

        validateSave(name, phone)
        val contact = ContactEntity(name, phone)
        ContactRepository.save(contact)


    }

    fun delete(name: String, phone: String) {

        validateDelete(name, phone)
        val contact = ContactEntity(name, phone)
        ContactRepository.delete(contact)

    }

    fun getList(): MutableList<ContactEntity> {

        return ContactRepository.getList()
    }

}