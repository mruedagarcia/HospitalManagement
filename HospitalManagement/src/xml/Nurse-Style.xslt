<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Id</th>
      <th>email</th>
      <th>name</th>
      <xsl:for-each select="Nurse">
      <xsl:sort select="@id" data-type="number"/>
            <tr>
            <td><i><xsl:value-of select="@id" /></i></td>
            <td><xsl:value-of select="email" /></td>
            <td><xsl:value-of select="name" /></td>
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>