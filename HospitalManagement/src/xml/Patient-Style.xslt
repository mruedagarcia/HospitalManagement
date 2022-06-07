<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Id</th>
      <th>email</th>
      <th>name</th>
	  <th>phone</th>
      <xsl:for-each select="Patient">
      <xsl:sort select="@id" data-type="number"/>
            <tr>
            <td><i><xsl:value-of select="@id" /></i></td>
            <td><xsl:value-of select="email" /></td>
            <td><xsl:value-of select="name" /></td>
			<td><xsl:value-of select="phone" /></td>
            </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>