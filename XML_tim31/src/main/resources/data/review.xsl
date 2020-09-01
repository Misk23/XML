<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rw="http://www.ftn.uns.ac.rs/XML/Review">

    <xsl:template match="/">
        <html>
            <head>
                <title>Review</title>
            </head>
            <body
                    style="font-family: Times New Roman; margin-left: 50px; margin-right: 50px;">


                <i>
                    Review id:
                    <xsl:value-of select="rw:review/@reviewId" />
                </i>

                <br/>

                <i>
                    Id of publication to review:
                    <xsl:value-of select="rw:review/@publicationId" />
                </i>

                <br/>

                <i>
                    Date of submission:
                    <xsl:value-of select="rw:review/@submissionDate" />
                </i>

                <br/>

                <i>
                    Reviewed by:
                    <xsl:value-of select="rw:review/@reviewedBy" />
                </i>

                <br/>
                <br/>

                <b style="font-size: 20px;">Overall evaluation: </b>
                <p style="text-align: left; font-weight: normal;">
                    <p style="float:left; margin-left:30px">
                        <xsl:value-of select="rw:review/rw:overallEvaluation" />
                        <br/>
                        </p>
                    <br/>
                </p>

                <br/>
                <br/>


                <b style="font-size: 20px;">Comments to author: </b>
                <p style="text-align: left; font-weight: normal;">
                    <p style="float:left; margin-left:30px">
                        <xsl:value-of select="rw:review/rw:commentToAuthor" />
                        <br/>
                    </p>
                    <br/>
                </p>

                <br/>
                <br/>

                <b style="font-size: 20px;">Comments to editor: </b>
                <p style="text-align: left; font-weight: normal;">
                    <p style="float:left; margin-left:30px">
                        <xsl:value-of select="rw:review/rw:commentsToEditor" />
                        <br/>
                    </p>
                    <br/>
                </p>

                <br/>
                <br/>

                <b style="font-size: 20px;">Criteria evaluations: </b>
                <br/>
                <br/>
                <p style="text-align: left; font-weight: normal;">

                    <i>
                        Relevance of research problem:
                        <xsl:value-of select="rw:review/rw:criteriaEvaluation/rw:relevanceOfResearchProblem" />
                    </i>
                    <br/>
                    <br/>

                    <i>
                        Conceptual quality:
                        <xsl:value-of select="rw:review/rw:criteriaEvaluation/rw:conceptualQuality" />
                    </i>
                    <br/>
                    <br/>

                    <i>
                        Methodological quality:
                        <xsl:value-of select="rw:review/rw:criteriaEvaluation/rw:methodologicalQuality" />
                    </i>
                    <br/>
                    <br/>

                    <i>
                        Readability:
                        <xsl:value-of select="rw:review/rw:criteriaEvaluation/rw:readability" />
                    </i>
                    <br/>
                    <br/>

                    <i>
                        Originality:
                        <xsl:value-of select="rw:review/rw:criteriaEvaluation/rw:originality" />
                    </i>
                    <br/>
                    <br/>
                </p>



                <br/>
                <br/>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>