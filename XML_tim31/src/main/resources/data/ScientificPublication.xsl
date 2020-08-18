<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:sp="http://www.ftn.uns.ac.rs/XML/ScientificPublication">

    <xsl:template match="/">
        <html>
            <head>
                <title>Scientific Publication</title>
            </head>
            <body
                    style="font-family: Times New Roman; margin-left: 50px; margin-right: 50px;">
                <h1 style="text-align: center; font-weight: bold;">
                    <xsl:value-of select="sp:scientificPublication/sp:metadata/sp:title" />
                </h1>

                <br/>

                <i>
                    Publication id:
                        <xsl:value-of select="sp:scientificPublication/@publicationId" />
                </i>

                <br/>

                <i>
                    Date of submission:
                    <xsl:value-of
                            select="sp:scientificPublication/@submissionDate" />
                </i>

                <br/>

                <i>
                    Language:
                    <xsl:value-of
                            select="sp:scientificPublication/@language" />
                </i>

                <br />

                <i>
                    Status:
                    <xsl:value-of
                            select="sp:scientificPublication/@status" />
                </i>

                <br/>
                <br/>

                <b style="font-size: 20px;">Authors: </b>
                <p style="text-align: left; font-weight: normal;">
                    <xsl:for-each
                            select="sp:scientificPublication/sp:metadata/sp:authors">
                        <p style="float:left; margin-left:30px">
                           <xsl:value-of select="sp:authorUsername" />
                            <!--<p><xsl:value-of select="."/></p>-->
                            <br/>
                        </p>
                        <br/>
                    </xsl:for-each>
                </p>



                <br />

                <b style="font-size: 20px;">Reviewers: </b>
                <p style="text-align: left; font-weight: normal;">
                    <xsl:for-each
                            select="sp:scientificPublication/sp:metadata/sp:reviewers">
                        <p style="float:left; margin-left:30px">
                            <xsl:value-of select="sp:reviewerUsername" />
                            <br/>
                        </p>
                        <br/>
                    </xsl:for-each>
                </p>

                <br/>

                <b>Keywords:</b>
                <xsl:for-each select="sp:scientificPublication/sp:metadata/sp:keywords">
                    <p style="float:left; margin-left:30px"><xsl:value-of select="."/></p>
                </xsl:for-each>


                <xsl:for-each select="sp:scientificPublication/sp:chapter">
                    <h3 style="text-align: center;"><xsl:value-of select="sp:title"/></h3>
                    <xsl:for-each select="sp:paragraph">
                        <p><xsl:value-of select="sp:text"/></p>

                        <xsl:choose>

                            <xsl:when test="sp:citation">
                                <p><b>Citation <xsl:value-of select="sp:citation/@id"/>:</b></p>
                                <p style="float:left; margin-left:30px">Name of author: <i><xsl:value-of select="sp:citation/sp:authorNames"/></i></p><br/>
                                <p style="float:left; margin-left:30px">Year: <i><xsl:value-of select="sp:citation/sp:year"/></i></p><br/>
                                <p style="float:left; margin-left:30px">Text of citation: <i><xsl:value-of select="sp:citation/sp:text"/></i></p><br/>
                            </xsl:when>
                        </xsl:choose>


                    </xsl:for-each>
                    <br/>
                </xsl:for-each>



                <xsl:for-each select="sp:scientificPublication/sp:reference">
                    <p><b>Reference to citation <xsl:value-of select="@citationId"/>:</b></p>
                    <p style="float:left; margin-left:30px">Name of author: <i><xsl:value-of select="sp:authorNames"/></i></p><br/>
                    <p style="float:left; margin-left:30px">Title of publication: <i><xsl:value-of select="sp:publicationTitle"/></i></p><br/>
                    <p style="float:left; margin-left:30px">Year: <i><xsl:value-of select="sp:year"/></i></p><br/>
                    <p style="float:left; margin-left:30px">Url: <i><xsl:value-of select="sp:url"/></i></p><br/>

                </xsl:for-each>



            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>