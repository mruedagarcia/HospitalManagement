<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Id</th>
      <th>Doctor</th>
      <th>Specialty</th>
      <xsl:for-each select="Doctor">
      <xsl:sort select="@id" data-type="number"/>
            <tr>
            <td><i><xsl:value-of select="@id" /></i></td>
            <td><xsl:value-of select="name" /></td>
            <td><xsl:value-of select="specialty" /></td>
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>