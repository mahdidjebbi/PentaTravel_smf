<?php

namespace App\Controller;

use App\Entity\User;
use App\Repository\UserRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

class UserMobileController extends AbstractController
{
    /**
     * @Route("/user/mobile", name="user_mobile")
     */
    public function index(): Response
    {
        return $this->render('user_mobile/index.html.twig', [
            'controller_name' => 'UserMobileController',
        ]);
    }
    /**
     * @Route ("/AjouterUser"   , name="ajouterUser", methods={"GET", "POST"})
     */
    public function ajouter(Request $request,UserPasswordEncoderInterface $encoder){

        $user = new User();

        $em=$this->getDoctrine()->getManager();

        $name=$request->query->get("name");
        $user->setNom($name);

        $prenom=$request->query->get("prenom");
        $user->setPrenom($prenom);

        $age=$request->query->get("age");
        $user->setAge($age);

        $email=$request->query->get("email");
        $user->setEmail($email);

        $number=$request->query->get("number");
        $user->setNumber($number);

        $password=$request->query->get("Password");
        $hash = $encoder->encodePassword($user, $password);
        $user->setPassword($hash);
        $user->setFlag(1);
        if(!filter_var($email,FILTER_VALIDATE_EMAIL))
        {
            return new Response("invalid email");

        }

        $em->persist($user);
        $em->flush();


        $serializer = new Serializer([new ObjectNormalizer()]);
        $aj = $serializer->normalize($user);
        return new JsonResponse($aj);

    }

    /**
     * @param UserRepository $rep
     * @return Response
     * @Route("/display")
     */
    public function showuser(UserRepository $rep,SerializerInterface $serializer)
    {
        $users =$rep->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $aj = $serializer->normalize($users);
        return new JsonResponse($aj);


    }
    /**
     * @Route ("/Deleteuser")
     */
    function deleteUser(Request $request , UserRepository $repository){
        $id=$request->get("id");
        $em=$this->getDoctrine()->getManager();

        $user =$em->getRepository(User::class)->find($id);
        $em->remove($user);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $aj = $serializer->normalize("le user a ete supprimee avec success.");
        return new JsonResponse($aj);
    }


    /******************Modifier  Mobile*****************************************/
    /**
     * @Route("/updateUser", name="update_user")
     */
    public function UpdateUser(Request $request,UserPasswordEncoderInterface $encoder) {
        $em = $this->getDoctrine()->getManager();
        $user = $this->getDoctrine()->getManager()
            ->getRepository(User::class)
            ->find($request->get("id"));

        $user->setNom($request->get("name"));
        $user->setPrenom($request->get("prenom"));
        $user->setAge($request->get("age"));
        $hash = $encoder->encodePassword($user, $request->get("Password"));
        $user->setPassword($hash);
        $user->setEmail($request->get("email"));
        $user->setNumber($request->get("number"));


        $em->persist($user);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($user);
        return new JsonResponse("User a ete modifiee avec success.");

    }
    /**
 * @Route("/updatePassword", name="updatepassword")
 */
    public function Updatepassword(Request $request,UserPasswordEncoderInterface $encoder) {
        $em = $this->getDoctrine()->getManager();
        $user = $this->getDoctrine()->getManager()
            ->getRepository(User::class)
            ->find($request->get("id"));


        $hash = $encoder->encodePassword($user, $request->get("Password"));
        $user->setPassword($hash);



        $em->persist($user);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($user);
        return new JsonResponse("le password estmodifiee avec success.");

    }
    /**
     * @Route("/loginmobile", name="login_")
     */
    public function LoginMobile(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $user = $this->getDoctrine()->getManager()
            ->getRepository(User::class)
            ->findOneBy(['email'=>$request->get("email")]);

        if($user)
        {
            if(password_verify($request->get("Password"),$user->getPassword()))
            {
                $serializer = new Serializer([new ObjectNormalizer()]);
                $formatted = $serializer->normalize($user);
                return new JsonResponse($formatted);
            }
            else
            {
                return new Response("password incorect");
            }
        }






    }
}
