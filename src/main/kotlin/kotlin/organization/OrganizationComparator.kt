package organization

class OrganizationComparator : Comparator<Organization?> {
    override fun compare(a: Organization?, b: Organization?): Int {
        if (a!!.getPostalAddressZipCode() === b!!.getPostalAddressZipCode()) {
            return 0
        }
        return if ( a!!.getPostalAddressZipCode() > b!!.getPostalAddressZipCode() ) {
            1
        } else {
            -1
        }
    }
}