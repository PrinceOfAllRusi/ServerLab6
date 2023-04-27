package tools

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import organization.MyCollection
import organization.Organization
import organization.OrganizationType
import tools.input.Input
import tools.input.InputFile
import java.lang.Integer.max


class CreateOrganization: KoinComponent {

    private val orgs: MyCollection<Organization> by inject()
    private var counter: Int = 0



    fun create(data: Map<String, String?>, lastOrganization: Organization?): Organization {

        val org: Organization = Organization()

        var name = data["name"]!!
        var annualTurnover: Double = data["annualTurnover"]!!.toDouble()
        var employeesCount: Int = data["employeesCount"]!!.toInt()
        var x: Int = data["x"]!!.toInt()
        var y: Long = data["y"]!!.toLong()
        var type: OrganizationType = OrganizationType.valueOf(data["type"]!!)
        var street = data["street"]!!
        var zipCode = data["zipCode"]!!

        if (data["name"]!!.isBlank()) {
            name = lastOrganization!!.getName()!!
        }
        if (data["annualTurnover"]!!.isBlank()) {
            annualTurnover = lastOrganization!!.getAnnualTurnover()!!
        }
        if (data["employeesCount"]!!.isBlank()) {
            employeesCount = lastOrganization!!.getEmployeesCount()!!
        }
        if (data["x"]!!.isBlank()) {
            x = lastOrganization!!.getCoordinatesX()!!.toInt()
        }
        if (data["y"]!!.isBlank()){
            y = lastOrganization!!.getCoordinatesY()!!.toLong()
        }
        if (data["type"]!!.isBlank()){
            type = lastOrganization!!.getType()!!
        }
        if (data["street"]!!.isBlank()){
            street = lastOrganization!!.getPostalAddressStreet()
        }
        if (data["zipCode"]!!.isBlank()){
            zipCode = lastOrganization!!.getPostalAddressZipCode()
        }


        org.setName(name)
        org.setAnnualTurnover(annualTurnover)
        org.setEmployeesCount(employeesCount)
        org.setCoordinatesX(x)
        org.setCoordinatesY(y)
        org.setType(type)
        org.setPostalAddressStreet(street)
        org.setPostalAddressZipCode(zipCode)

        if ( orgs.size != 0 ) {
            for ( organization in orgs ) {
                counter = max( counter, organization.getId()!!  )
            }
        }

        org.setId(counter)

        counter++

        return org
    }
}
