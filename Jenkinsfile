node  {
        
       try
          {
 
   echo "doing a maven clean buils"
   
    
   //source code checkout is pulling from Gitlab to Jenkins workspace directory
    stage 'Checkout'
         checkout scm
     
       
    stage 'Build'
              rtMaven.run pom: 'pom.xml', goals: 'clean install -Dbuild.number=1 -DskipTests=true -Dmaven.repo.local=.m2', buildInfo: buildInfo
         server.publishBuildInfo buildInfo
   
   }catch (Exception e)
    {
        println e.getMessage()
     
    }finally {
        echo "cleaning up workspace"        
        currentBuild.result = "SUCCESS"
     }
    
}
 
